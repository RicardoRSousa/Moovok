package com.ricardojrsousa.movook.presentation.discover

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Genre
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.BindableViewListAdapter
import com.ricardojrsousa.movook.presentation.viewHolders.GenreViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.discover_fragment_2.view.*
import kotlinx.android.synthetic.main.discover_fragment_3.view.*
import kotlinx.android.synthetic.main.fragment_discover.view.*

/**
 * Created by Ricardo Sousa on 07/12/2020
 */

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<DiscoverViewModel>(R.layout.fragment_discover) {

    override val viewModel: DiscoverViewModel by viewModels()

    var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(view)
    }

    private fun setupView(view: View, genresList: List<Genre>) {
        with(view) {

            discover_coordinator.addPage(R.layout.discover_fragment_1, R.layout.discover_fragment_2, R.layout.discover_fragment_3)
            discover_coordinator.setScrollingEnabled(false)

            genres_list.layoutManager = object : GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            genres_list.adapter = createGenresListAdapter(genresList)

            year_range_seek_bar.setIndicatorTextDecimalFormat("0")

            next.setOnClickListener {
                discover_coordinator.setCurrentPage(currentPage++, true)
            }
        }
    }

    private fun createGenresListAdapter(genresList: List<Genre>): BindableViewListAdapter<Genre> {
        val adapter = BindableViewListAdapter(GenreViewHolder())
        adapter.addItems(genresList)
        return adapter
    }

    private fun observeViewModel(view: View) {
        viewModel.genres.observe(viewLifecycleOwner, {
            startPostponedEnterTransition()
            setupView(view, it)
        })
    }
}

