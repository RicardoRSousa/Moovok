package com.ricardojrsousa.movook.presentation.topratedmovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.BindableViewListAdapter
import com.ricardojrsousa.movook.presentation.viewHolders.TopRatedMovieViewHolder
import com.ricardojrsousa.movook.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_rated_movies.view.*

@AndroidEntryPoint
class TopRatedMoviesFragment : BaseFragment<TopRatedMoviesViewModel>(R.layout.fragment_top_rated_movies) {

    override val viewModel: TopRatedMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topRatedMoviesAdapter = createtopRatedMoviesAdapter()
        setupRecyclerView(view, topRatedMoviesAdapter)
        observeViewModel(topRatedMoviesAdapter)
    }

    private fun observeViewModel(topRatedMoviesAdapter: BindableViewListAdapter<Movie>) {
        viewModel.topRatedMovies.observe(viewLifecycleOwner, {
            topRatedMoviesAdapter.addItems(it)
            startPostponedEnterTransition()
        })
    }

    private fun setupRecyclerView(view: View, topRatedMoviesAdapter: BindableViewListAdapter<Movie>) {
        view.top_rated_movies_recycler_view.run {
            adapter = topRatedMoviesAdapter
            layoutManager =  LinearLayoutManager(requireContext(),  VERTICAL, false)

            addOnScrollListener(object : PaginationScrollListener(layoutManager as LinearLayoutManager) {
                override fun isLastPage(): Boolean {
                    return viewModel.isLastPage()
                }

                override fun isLoading(): Boolean {
                    return viewModel.isLoading()
                }

                override fun loadNextPage() {
                    viewModel.loadNextPageOfTopRatedMovies()
                }
            })
        }
    }

    private fun createtopRatedMoviesAdapter(): BindableViewListAdapter<Movie> {
        val adapter = BindableViewListAdapter(TopRatedMovieViewHolder()) { view, movie ->
            showLoading()
            if (movie != null) {
                val action = TopRatedMoviesFragmentDirections.actionTopRatedMoviesFragmentToMovieDetailsFragment(movie.id)
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

}