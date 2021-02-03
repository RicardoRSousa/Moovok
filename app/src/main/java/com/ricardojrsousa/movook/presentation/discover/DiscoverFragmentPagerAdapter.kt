package com.ricardojrsousa.movook.presentation.discover

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Ricardo Sousa on 21/01/2021.
 */
class DiscoverFragmentPagerAdapter(fm: Fragment, private val pages: List<Fragment>) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }
}