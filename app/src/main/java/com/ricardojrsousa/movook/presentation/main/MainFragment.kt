package com.ricardojrsousa.movook.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.BindableViewListAdapter
import com.ricardojrsousa.movook.presentation.viewHolders.MoviePosterViewHolder
import com.ricardojrsousa.movook.utils.PaginationScrollListener
import com.ricardojrsousa.movook.wrappers.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel>(R.layout.fragment_main), LifecycleObserver {

    override val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesInTheatresAdapter = createMoviesInTheatresAdapter()
        setupRecyclerView(view, moviesInTheatresAdapter)
        observeViewModel(moviesInTheatresAdapter)

        startPostponedEnterTransition()

        setupView()

        viewLifecycleOwner.lifecycle.addObserver(this)
    }

    private fun setupView() {
        option_discover.setOnClickListener {
            navigate(MainFragmentDirections.actionMainFragmentToDiscoverFragment())
        }
        //option_upcoming.setOnClickListener { showComingSoonToast() }
        option_top_rated.setOnClickListener {
            showLoading()
            navigate(MainFragmentDirections.actionMainFragmentToTopRatedMoviesFragment())
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onFragmentCreate() {
        if (viewModel.loadedItems.isEmpty())
            showLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewLifecycleOwner.lifecycle.removeObserver(this)
    }

    private fun showComingSoonToast() = Toast.makeText(requireContext(), R.string.coming_soon, Toast.LENGTH_LONG).show()

    private fun setupRecyclerView(view: View, movieListAdapter: BindableViewListAdapter<Movie>) {
        view.movies_in_theatres_list.run {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            addOnScrollListener(object : PaginationScrollListener(layoutManager as LinearLayoutManager) {
                override fun isLastPage(): Boolean {
                    return viewModel.isLastPage()
                }

                override fun isLoading(): Boolean {
                    return viewModel.isLoading()
                }

                override fun loadNextPage() {
                    viewModel.loadNextPageOfNowPlayingMovies()
                }

            })
        }
    }

    private fun createMoviesInTheatresAdapter(): BindableViewListAdapter<Movie> {
        val adapter = BindableViewListAdapter(MoviePosterViewHolder()) { view, movie ->
            showLoading()
            if (movie != null) {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetailsFragment(movie.id)
                if (view != null) {
                    val extras = FragmentNavigatorExtras(view to movie.id)
                    navigate(action, extras)
                } else {
                    navigate(action)
                }
            }
        }
        adapter.addItems(viewModel.loadedItems)
        return adapter
    }

    private fun observeViewModel(movieListAdapter: BindableViewListAdapter<Movie>) {
        viewModel.moviesInTheatres.observe(viewLifecycleOwner, {
            hideLoading()
            movieListAdapter.addItems(it)
        })

        viewModel.topRatedMovieBackdrop.observe(viewLifecycleOwner, {
            options_top_rated_background.loadImage(it)
        })
    }

}
