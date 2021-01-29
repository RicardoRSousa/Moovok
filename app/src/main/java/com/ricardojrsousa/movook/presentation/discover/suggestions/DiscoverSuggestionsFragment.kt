package com.ricardojrsousa.movook.presentation.discover.suggestions

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.BindableViewListAdapter
import com.ricardojrsousa.movook.presentation.discover.DiscoverViewModel
import com.ricardojrsousa.movook.utils.PaginationScrollListener
import com.ricardojrsousa.movook.presentation.viewHolders.MovieSuggestionViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_discover_suggestions.*
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout


@AndroidEntryPoint
class DiscoverSuggestionsFragment : BaseFragment<DiscoverViewModel>(R.layout.fragment_discover_suggestions), LifecycleObserver {

    private lateinit var suggestionsListAdapter: BindableViewListAdapter<Movie>
    override lateinit var viewModel: DiscoverViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment(), defaultViewModelProviderFactory).get(DiscoverViewModel::class.java)
        viewModel.loadSuggestions()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeViewModel()
        startPostponedEnterTransition()

        viewLifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewLifecycleOwner.lifecycle.removeObserver(this)
    }

    private fun setupView() {

        suggestionsListAdapter = BindableViewListAdapter(MovieSuggestionViewHolder()) { view, movie ->
            showLoading()
            if (movie != null) {
                val action = DiscoverSuggestionsFragmentDirections.actionDiscoverSuggestionsFragmentToMovieDetailsFragment(movie.id)
                if (view != null) {
                    val extras = FragmentNavigatorExtras(view to movie.id)
                    navigate(action, extras)
                } else {
                    navigate(action)
                }
            }
        }

        val zoomRecyclerLayoutManager = ZoomRecyclerLayout(requireContext()).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }

        with(suggestions_recycler_view) {
            layoutManager = zoomRecyclerLayoutManager
            LinearSnapHelper().attachToRecyclerView(this)
            isNestedScrollingEnabled = false
            adapter = suggestionsListAdapter

            addOnScrollListener(object : PaginationScrollListener(zoomRecyclerLayoutManager) {
                override fun isLastPage(): Boolean {
                    return viewModel.isLastPage()
                }

                override fun isLoading(): Boolean {
                    return viewModel.isLoading()
                }

                override fun loadNextPage() {
                    viewModel.loadSuggestions()
                }
            })

        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onFragmentPause() {
        viewModel.resetCurrentSuggestionsPage()
    }

    private fun setSuggestionsFoundCounter(count: Int) {
        if (count > 0) {
            suggestions_count.text = resources.getString(R.string.suggestions_count, count)
        } else {
            suggestions_count.text = resources.getString(R.string.suggestions_empty)
        }
    }

    private fun observeViewModel() {
        viewModel.discoveredMoviesList.observe(viewLifecycleOwner) {
            suggestionsListAdapter.addItems(it)
        }

        viewModel.discoveredMoviesCount.observe(viewLifecycleOwner) {
            setSuggestionsFoundCounter(it)
            if (it == 0) {
                empty_results.visibility = View.VISIBLE
            } else {
                empty_results.visibility = View.GONE
            }
        }
    }


}