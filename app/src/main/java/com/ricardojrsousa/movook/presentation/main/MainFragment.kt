package com.ricardojrsousa.movook.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.presentation.BookListAdapter
import com.ricardojrsousa.movook.presentation.MovieListAdapter
import com.ricardojrsousa.movook.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        viewModel = MainViewModelFactory(requireActivity().applicationContext).create(MainViewModel::class.java)
        navController = findNavController()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val upcomingMoviesListAdapter = createUpcomingMoviesAdapter()
        setupRecyclerView(view, upcomingMoviesListAdapter)
        observeViewModel(upcomingMoviesListAdapter)

        return view
    }


    private fun setupRecyclerView(view: View, movieListAdapter: MovieListAdapter) {

        view.upcoming_movies_list.run {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            postponeEnterTransition()
            doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }

    private fun createUpcomingMoviesAdapter(): MovieListAdapter {
        return MovieListAdapter { view, movie ->
            val extras = FragmentNavigatorExtras(view to movie.posterPath)
            val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movie.id)
            navController.navigate(action, extras)
        }
    }

    private fun observeViewModel(movieListAdapter: MovieListAdapter) {
        viewModel.upcomingMovies.observe(viewLifecycleOwner,
            Observer { movieListAdapter.addItems(it) })

    }
}
