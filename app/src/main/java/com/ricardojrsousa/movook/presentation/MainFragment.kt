package com.ricardojrsousa.movook.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.presentation.details.MovieDetailsFragment
import com.ricardojrsousa.movook.presentation.details.MovieDetailsFragmentArgs
import com.ricardojrsousa.movook.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.upcoming_movie_list_item.*

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


    private fun setupRecyclerView(view: View, upcomingMoviesListAdapter: UpcomingMoviesListAdapter) {

        view.upcoming_movies_list.run {
            adapter = upcomingMoviesListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            postponeEnterTransition()
            doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }

    private fun createUpcomingMoviesAdapter(): UpcomingMoviesListAdapter {
        return UpcomingMoviesListAdapter { view, movie ->
            val extras = FragmentNavigatorExtras(view to movie.posterPath)
            val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movie.id)
            navController.navigate(action, extras)
        }
    }

    private fun observeViewModel(upcomingMoviesListAdapter: UpcomingMoviesListAdapter) {
        viewModel.upcomingMovies.observe(viewLifecycleOwner,
            Observer { upcomingMoviesListAdapter.addItems(it) })
    }
}
