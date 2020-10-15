package com.ricardojrsousa.movook.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.adapters.BindableViewListAdapter
import com.ricardojrsousa.movook.presentation.views.MoviePosterView
import com.ricardojrsousa.movook.wrappers.PicassoWrapper
import com.ricardojrsousa.movook.wrappers.loadBookCover
import com.ricardojrsousa.movook.wrappers.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel>(R.layout.fragment_main) {

    override val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesInTheatresAdapter = createMoviesInTheatresAdapter()
        setupRecyclerView(view, moviesInTheatresAdapter)
        observeViewModel(moviesInTheatresAdapter)

        setupView()
    }

    private fun setupView() {
        option_discover.setOnClickListener { showComingSoonToast() }
        option_upcoming.setOnClickListener { showComingSoonToast() }
        option_most_popular.setOnClickListener { navigate(MainFragmentDirections.actionMainFragmentToPopularMoviesFragment()) }
    }

    private fun showComingSoonToast() = Toast.makeText(requireContext(), R.string.coming_soon, Toast.LENGTH_LONG).show()

    private fun setupRecyclerView(view: View, movieListAdapter: BindableViewListAdapter<Movie>) {
        view.movies_in_theatres_list.run {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            postponeEnterTransition()
            doOnPreDraw {
                startPostponedEnterTransition()
            }

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
        val adapter = BindableViewListAdapter(MoviePosterView()) { view, movie ->
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
        viewModel.moviesInTheatres.observe(viewLifecycleOwner, { movieListAdapter.addItems(it) })

        viewModel.popularMovieBackdrop.observe(viewLifecycleOwner, {
            options_most_popular_background.loadImage(it)
        })
    }

}
