package com.ricardojrsousa.movook.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.MovieDetails
import com.ricardojrsousa.movook.presentation.BookListAdapter
import com.ricardojrsousa.movook.presentation.MovieListAdapter
import com.ricardojrsousa.movook.viewmodel.MovieDetailsViewModelFactory
import com.ricardojrsousa.movook.wrappers.loadMovieBackdrop
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.view.*


class MovieDetailsFragment : Fragment() {
    private lateinit var viewModel: MovieDetailsViewModel
    private var movieId: Int = 0

    val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = args.movieId
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        viewModel = MovieDetailsViewModelFactory(requireActivity().applicationContext, movieId).create(MovieDetailsViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)

        val similarMoviesAdapter = createSimilarMoviesAdapter()
        val relatedBooksAdapter = createRelatedBooksAdapter()

        setupRecyclerView(view, similarMoviesAdapter, relatedBooksAdapter)
        observeViewModel(similarMoviesAdapter, relatedBooksAdapter)
        postponeEnterTransition()

        return view
    }

    private fun setupRecyclerView(view: View, movieListAdapter: MovieListAdapter, booksListAdapter: BookListAdapter) {

        view.similar_movies_list.run {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        view.related_books_list.run {
            adapter = booksListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    //TODO: On click similiar movies should go to that movie details
    private fun createSimilarMoviesAdapter(): MovieListAdapter = MovieListAdapter { view, movie ->
        //val extras = FragmentNavigatorExtras(view to movie.posterPath)
        //val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movie.id)
        //navController.navigate(action, extras)
    }


    //TODO: On click related books should go to that book details
    private fun createRelatedBooksAdapter(): BookListAdapter = BookListAdapter { view, book ->
        //val extras = FragmentNavigatorExtras(view to movie.posterPath)
        //val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movie.id)
        //navController.navigate(action, extras)
    }


    private fun observeViewModel(similarMoviesAdapter: MovieListAdapter, relatedBooksAdapter: BookListAdapter) {
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            setupView(it)
            getRelatedBooks(it.originalTitle!!)
        })

        viewModel.similarMovies.observe(viewLifecycleOwner,
            Observer { similarMoviesAdapter.addItems(it) })

        viewModel.relatedBooks.observe(viewLifecycleOwner,
            Observer { relatedBooksAdapter.addItems(it) })
    }

    private fun getRelatedBooks(movieName: String) = viewModel.getRelatedBooks(movieName)

    private fun setupView(movie: MovieDetails) {
        movie_backdrop_image.loadMovieBackdrop(movie.backdropPath)
        movie_poster_image.transitionName = movie.posterPath
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
    }
}