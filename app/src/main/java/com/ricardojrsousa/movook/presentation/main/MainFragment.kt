package com.ricardojrsousa.movook.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.adapters.MovieListAdapter
import com.ricardojrsousa.movook.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : BaseFragment<MainViewModel>() {

    override lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModelFactory(requireActivity().applicationContext).create(MainViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val moviesInTheatresAdapter = createMoviesInTheatresAdapter()
        setupRecyclerView(view, moviesInTheatresAdapter)
        observeViewModel(moviesInTheatresAdapter)

        return view
    }


    private fun setupRecyclerView(view: View, movieListAdapter: MovieListAdapter) {

        view.movies_in_theatres_list.run {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            postponeEnterTransition()
            doOnPreDraw {
                startPostponedEnterTransition()
            }
        }
    }

    private fun createMoviesInTheatresAdapter(): MovieListAdapter {
        return MovieListAdapter { view, movie ->
            if (movie != null) {
                val extras = FragmentNavigatorExtras(view to movie.id.toString())
                val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movie.id)
                navController.navigate(action, extras)
            }
        }
    }

    private fun observeViewModel(movieListAdapter: MovieListAdapter) {
        viewModel.moviesInTheatres.observe(viewLifecycleOwner,
            Observer { movieListAdapter.addItems(it) })
    }

}
