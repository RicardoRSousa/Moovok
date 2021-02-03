package com.ricardojrsousa.movook.presentation.discover

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Genre
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.discover.wizard.DiscoverFragmentStep1
import com.ricardojrsousa.movook.presentation.discover.wizard.DiscoverFragmentStep2
import com.ricardojrsousa.movook.presentation.discover.wizard.DiscoverFragmentStep3
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.fragment_discover.view.*

/**
 * Created by Ricardo Sousa on 07/12/2020
 */

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<DiscoverViewModel>(R.layout.fragment_discover) {

    private var tabMediator: TabLayoutMediator? = null
    override lateinit var viewModel: DiscoverViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment(), defaultViewModelProviderFactory).get(DiscoverViewModel::class.java)
        viewModel.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(view)
    }

    private fun setupView(view: View, genresList: List<Genre>) {
        val pages = listOf(DiscoverFragmentStep1(), DiscoverFragmentStep2(genresList), DiscoverFragmentStep3())

        view_pager.adapter = DiscoverFragmentPagerAdapter(this@DiscoverFragment, pages)
        tabMediator = TabLayoutMediator(tab_dots, view_pager) { _, _ -> }
        tabMediator!!.attach()
    }

    private fun observeViewModel(view: View) {
        viewModel.genres.observe(viewLifecycleOwner, {
            setupView(view, it)
            startPostponedEnterTransition()
        })

        viewModel.wizardConclusion.observe(viewLifecycleOwner, {
            showLoading()
            navigate(DiscoverFragmentDirections.actionDiscoverFragmentToDiscoverSuggestionsFragment())
        })
    }

    override fun onDestroyView() {
        view_pager.adapter = null
        tabMediator?.detach()
        tabMediator = null
        super.onDestroyView()
    }
}