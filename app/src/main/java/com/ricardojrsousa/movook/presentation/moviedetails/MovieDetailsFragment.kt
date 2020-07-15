package com.ricardojrsousa.movook.presentation.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.guilhe.views.CircularProgressView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.MovieDetails
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.adapters.BookListAdapter
import com.ricardojrsousa.movook.presentation.adapters.CastListAdapter
import com.ricardojrsousa.movook.presentation.adapters.MovieListAdapter
import com.ricardojrsousa.movook.viewmodel.MovieDetailsViewModelFactory
import com.ricardojrsousa.movook.wrappers.loadMovieBackdrop
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.movie_backdrop_image
import kotlinx.android.synthetic.main.fragment_movie_details.movie_description
import kotlinx.android.synthetic.main.fragment_movie_details.movie_poster_image
import kotlinx.android.synthetic.main.fragment_movie_details.movie_tagline
import kotlinx.android.synthetic.main.fragment_movie_details.movie_title
import kotlinx.android.synthetic.main.fragment_movie_details.movie_year
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import kotlinx.android.synthetic.main.fragment_movie_details.view.related_books_list
import kotlinx.android.synthetic.main.fragment_movie_details.view.similar_movies_list


class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel>() {


    override lateinit var viewModel: MovieDetailsViewModel
    private var movieId: Int = 0

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = args.movieId
        viewModel = MovieDetailsViewModelFactory(requireActivity().applicationContext, movieId).create(MovieDetailsViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)

        val similarMoviesAdapter = createSimilarMoviesAdapter()
        val relatedBooksAdapter = createRelatedBooksAdapter()
        val castAdapter = createCastAdapter()

        setupRecyclerView(view, similarMoviesAdapter, relatedBooksAdapter, castAdapter)
        observeViewModel(similarMoviesAdapter, relatedBooksAdapter, castAdapter)
        postponeEnterTransition()

        return view
    }

    private fun setupRecyclerView(view: View, movieListAdapter: MovieListAdapter, booksListAdapter: BookListAdapter, castListAdapter: CastListAdapter) {

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

    private fun createSimilarMoviesAdapter(): MovieListAdapter =
        MovieListAdapter { view, movie ->
            if (movie != null) {
                val extras = FragmentNavigatorExtras(view to movie.id.toString())
                val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(movie.id)
                navController.navigate(action, extras)
            }
        }

    //TODO: On click related books should go to that book details
    private fun createRelatedBooksAdapter(): BookListAdapter =
        BookListAdapter { view, book ->
            //val extras = FragmentNavigatorExtras(view to movie.posterPath)
            //val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movie.id)
            //navController.navigate(action, extras)
        }

    private fun createCastAdapter(): CastListAdapter =
        CastListAdapter { view, person ->
            //val extras = FragmentNavigatorExtras(view to movie.posterPath)
            val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToPersonDetailsFragment(person.id)
            navController.navigate(action)
        }


    private fun observeViewModel(similarMoviesAdapter: MovieListAdapter, relatedBooksAdapter: BookListAdapter, castListAdapter: CastListAdapter) {
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            setupView(it)
            castListAdapter.addItems(it.credits)
        })

        viewModel.similarMovies.observe(viewLifecycleOwner,
            Observer { similarMoviesAdapter.addItems(it) })

        viewModel.relatedBooks.observe(viewLifecycleOwner,
            Observer { if (it != null) relatedBooksAdapter.addItems(it) })
    }

    private fun setupView(movie: MovieDetails) {
        movie_backdrop_image.loadMovieBackdrop(movie.backdropPath)
        movie_poster_image.transitionName = movie.id.toString()
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
        movie_vote_average.text = "${movie.voteAverage.toString()}/10"
        setVoteView(movie_vote_view, movie.voteAverage?.toFloat())

        if (movie.basedOnBook) {
            related_books_label.visibility = View.VISIBLE
            related_books_list.visibility = View.VISIBLE
        }
    }

    private fun setVoteView(view: CircularProgressView, voteAverage: Float? = 0f) {
        val color = when (voteAverage!!) {
            in 0f..3f -> R.color.votes_red
            in 3.1f..5f -> R.color.votes_orange
            in 5.1f..7f -> R.color.votes_yellow
            else -> R.color.votes_green
        }

        view.progressColor = resources.getColor(color)
        view.progress = voteAverage
    }
}