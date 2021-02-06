package com.ricardojrsousa.movook.presentation.moviedetails

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.*
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.BindableViewListAdapter
import com.ricardojrsousa.movook.presentation.viewHolders.BookCoverViewHolder
import com.ricardojrsousa.movook.presentation.viewHolders.CastPersonViewHolder
import com.ricardojrsousa.movook.presentation.viewHolders.MoviePosterViewHolder
import com.ricardojrsousa.movook.wrappers.loadMovieBackdrop
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import com.squareup.picasso.Callback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.view.*


@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>(R.layout.fragment_movie_details), YouTubePlayerListener {

    override val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var movieId: String

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = args.movieId
        viewModel.init(movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val similarMoviesAdapter = createSimilarMoviesAdapter()
        val relatedBooksAdapter = createRelatedBooksAdapter()
        val castAdapter = createCastAdapter()

        setupRecyclerView(view, similarMoviesAdapter, relatedBooksAdapter, castAdapter)
        observeViewModel(similarMoviesAdapter, relatedBooksAdapter, castAdapter)
    }

    private fun setupRecyclerView(
        view: View, movieListAdapter: BindableViewListAdapter<Movie>, booksListAdapter: BindableViewListAdapter<Book>,
        castListAdapter: BindableViewListAdapter<Person>
    ) {

        view.similar_movies_list.run {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        view.related_books_list.run {
            adapter = booksListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        view.cast_list.run {
            adapter = castListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun createSimilarMoviesAdapter(): BindableViewListAdapter<Movie> =
        BindableViewListAdapter(MoviePosterViewHolder()) { view, movie ->
            showLoading()
            if (movie != null) {
                val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(movie.id)
                if (view != null) {
                    val extras = FragmentNavigatorExtras(view to movie.id)
                    navigate(action, extras)
                } else {
                    navigate(action)
                }
            }
        }

    private fun createRelatedBooksAdapter(): BindableViewListAdapter<Book> =
        BindableViewListAdapter(BookCoverViewHolder()) { view, book ->
            //if (view != null) {
            showLoading()
            val extras = FragmentNavigatorExtras(view as ImageView to book!!.id)
            val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToBookDetailsFragment(book.id)
            navigate(action, extras)
            //}
        }

    private fun createCastAdapter(): BindableViewListAdapter<Person> =
        BindableViewListAdapter(CastPersonViewHolder()) { view, person ->
            showLoading()
            if (person != null) {
                val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToPersonDetailsFragment(person.id)
                navigate(action)
            }
        }


    private fun observeViewModel(
        similarMoviesAdapter: BindableViewListAdapter<Movie>, relatedBooksAdapter: BindableViewListAdapter<Book>,
        castListAdapter: BindableViewListAdapter<Person>
    ) {
        viewModel.movieDetails.observe(viewLifecycleOwner, {
            setupView(it)
            castListAdapter.addItems(it.credits)
        })

        viewModel.similarMovies.observe(viewLifecycleOwner, {
            setupSimilarMoviesVisibility(it.isNotEmpty())
            similarMoviesAdapter.addItems(it)
        })

        viewModel.relatedBooks.observe(viewLifecycleOwner, { if (it != null) relatedBooksAdapter.addItems(it) })

        viewModel.movieTrailer.observe(viewLifecycleOwner, { setupVideoView(it) })
    }

    private fun setupVideoView(movieTrailerUrl: String?) {
        movieTrailerUrl?.let { url ->
            lifecycle.addObserver(movie_trailer)
            movie_trailer.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(url, 0f)
                }
            })
            movie_trailer.addYouTubePlayerListener(this)
        }
    }

    private fun setupSimilarMoviesVisibility(hasSimilarMovies: Boolean) {
        if (!hasSimilarMovies) {
            similar_movies_label.visibility = View.GONE
            similar_movies_list.visibility = View.GONE
        }
    }

    private fun setupView(movie: MovieDetails) {
        movie_backdrop_image.loadMovieBackdrop(movie.backdropPath)
        movie_poster_image.transitionName = movie.id
        movie_poster_image.loadMoviePoster(movie.posterPath, object : Callback {
            override fun onSuccess() {
                startPostponedEnterTransition()
            }

            override fun onError(e: Exception?) {
                startPostponedEnterTransition()
            }
        })

        movie_description.text = movie.overview
        movie_title.text = movie.title
        movie_tagline.text = movie.tagline
        movie_year.text = movie.getReleaseYear()
        movie_genres.text = movie.getGenres()
        movie_runtime.text = "${movie.runtime} min."
        movie_vote_view.setVote(movie.voteAverage)

        if (movie.isBasedOnBook()) {
            related_books_label.visibility = View.VISIBLE
            related_books_list.visibility = View.VISIBLE
        }
    }

    override fun onApiChange(youTubePlayer: YouTubePlayer) {
        //Do nothing
    }

    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
        //Do nothing
    }

    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
        movie_trailer.visibility = View.INVISIBLE
        no_trailer_textview.visibility = View.VISIBLE

    }

    override fun onPlaybackQualityChange(youTubePlayer: YouTubePlayer, playbackQuality: PlayerConstants.PlaybackQuality) {
        //Do nothing
    }

    override fun onPlaybackRateChange(youTubePlayer: YouTubePlayer, playbackRate: PlayerConstants.PlaybackRate) {
        //Do nothing
    }

    override fun onReady(youTubePlayer: YouTubePlayer) {
        //Do nothing
    }

    override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
        //Do nothing
    }

    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
        //Do nothing
    }

    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
        //Do nothing
    }

    override fun onVideoLoadedFraction(youTubePlayer: YouTubePlayer, loadedFraction: Float) {
        //Do nothing
    }
}