package com.ricardojrsousa.movook.presentation.discover.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.*
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Genre
import com.ricardojrsousa.movook.presentation.BindableViewListAdapter
import com.ricardojrsousa.movook.presentation.discover.DiscoverViewModel
import com.ricardojrsousa.movook.presentation.viewHolders.GenreViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.discover_fragment_2.*


/**
 * Created by Ricardo Sousa on 21/01/2021.
 */

@AndroidEntryPoint
class DiscoverFragmentStep2(private val genresList: List<Genre>) : Fragment() {

    private lateinit var viewModel: DiscoverViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment().requireParentFragment(), defaultViewModelProviderFactory).get(DiscoverViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.discover_fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val genreAdapter = BindableViewListAdapter(GenreViewHolder(
            viewModel.getIncludedGenres()
        ) { genre, isChecked ->
            if (isChecked) {
                viewModel.includeGenre(genre)
            } else {
                viewModel.ignoreGenre(genre)
            }
        })
        genreAdapter.addItems(genresList)

        genres_list.apply {
            layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW, FlexWrap.WRAP)
            adapter = genreAdapter
        }
    }

}
