package com.ricardojrsousa.movook.presentation.popularmovies

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.adapters.BindableViewListAdapter
import com.ricardojrsousa.movook.presentation.main.MainFragmentDirections
import com.ricardojrsousa.movook.presentation.main.PaginationScrollListener
import com.ricardojrsousa.movook.presentation.views.MoviePosterView
import com.ricardojrsousa.movook.presentation.views.PopularMovieView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import kotlinx.android.synthetic.main.fragment_popular_movies.view.*

@AndroidEntryPoint
class PopularMoviesFragment : BaseFragment<PopularMoviesViewModel>(R.layout.fragment_popular_movies) {

    override val viewModel: PopularMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPostponedEnterTransition()

        val popularMoviesAdapter = createPopularMoviesAdapter()
        setupRecyclerView(view, popularMoviesAdapter)
        observeViewModel(popularMoviesAdapter)
    }

    private fun observeViewModel(popularMoviesAdapter: BindableViewListAdapter<Movie>) {
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            popularMoviesAdapter.addItems(it)
        })
    }

    private fun setupRecyclerView(view: View, popularMoviesAdapter: BindableViewListAdapter<Movie>) {
        view.popular_movies_recycler_view.run {
            adapter = popularMoviesAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            postponeEnterTransition()
            doOnPreDraw {
                startPostponedEnterTransition()
            }

            /*addOnScrollListener(object : PaginationScrollListener(layoutManager as LinearLayoutManager) {
                override fun isLastPage(): Boolean {
                  //  return viewModel.isLastPage()
                }

                override fun isLoading(): Boolean {
                    //return viewModel.isLoading()
                }

                override fun loadNextPage() {
                    //viewModel.loadNextPageOfNowPlayingMovies()
                }

            })*/
        }
    }

    private fun createPopularMoviesAdapter(): BindableViewListAdapter<Movie> {
        val adapter = BindableViewListAdapter(PopularMovieView()) { view, movie ->
            showLoading()
            if (movie != null) {
                val action = PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment(movie.id)
                if (view != null) {
                    val extras = FragmentNavigatorExtras(view to movie.id)
                    navigate(action, extras)
                } else {
                    navigate(action)
                }
            }
        }
        return adapter
    }


}