package com.ricardojrsousa.movook.presentation.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.*
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.adapters.BindableViewListAdapter
import com.ricardojrsousa.movook.presentation.main.MainFragmentDirections
import com.ricardojrsousa.movook.presentation.views.BookCoverView
import com.ricardojrsousa.movook.presentation.views.CastPersonView
import com.ricardojrsousa.movook.presentation.views.MoviePosterView
import com.ricardojrsousa.movook.wrappers.loadBackdrop
import com.ricardojrsousa.movook.wrappers.loadMovieBackdrop
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import com.squareup.picasso.Callback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.movie_description
import kotlinx.android.synthetic.main.fragment_movie_details.movie_poster_image
import kotlinx.android.synthetic.main.fragment_movie_details.movie_tagline
import kotlinx.android.synthetic.main.fragment_movie_details.movie_title
import kotlinx.android.synthetic.main.fragment_movie_details.movie_year
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import kotlinx.android.synthetic.main.fragment_movie_details.view.related_books_list
import kotlinx.android.synthetic.main.fragment_movie_details.view.similar_movies_list

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>(R.layout.fragment_movie_details) {

    override val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var movieId: String

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = args.movieId
        viewModel.init(movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        BindableViewListAdapter(MoviePosterView()) { view, movie ->
            showLoading()
            if (movie != null) {
                val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(movie.id)
                if (view != null) {
                    val extras = FragmentNavigatorExtras(view to movie.id)
                    navController.navigate(action, extras)
                } else {
                    navController.navigate(action)
                }
            }
        }

    private fun createRelatedBooksAdapter(): BindableViewListAdapter<Book> =
        BindableViewListAdapter(BookCoverView()) { view, book ->
            //if (view != null) {
            showLoading()
            val extras = FragmentNavigatorExtras(view as ImageView to book!!.id)
            val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToBookDetailsFragment(book.id)
            navController.navigate(action, extras)
            //}
        }

    private fun createCastAdapter(): BindableViewListAdapter<Person> =
        BindableViewListAdapter(CastPersonView()) { view, person ->
            showLoading()
            if (person != null) {
                val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToPersonDetailsFragment(person.id)
                navController.navigate(action)
            }
        }


    private fun observeViewModel(
        similarMoviesAdapter: BindableViewListAdapter<Movie>, relatedBooksAdapter: BindableViewListAdapter<Book>,
        castListAdapter: BindableViewListAdapter<Person>
    ) {
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            setupView(it)
            castListAdapter.addItems(it.credits)
        })

        viewModel.similarMovies.observe(viewLifecycleOwner,
            Observer {
                setupSimilarMoviesVisibility(!it.isEmpty())
                similarMoviesAdapter.addItems(it)
            })

        viewModel.relatedBooks.observe(viewLifecycleOwner,
            Observer { if (it != null) relatedBooksAdapter.addItems(it) })
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
}