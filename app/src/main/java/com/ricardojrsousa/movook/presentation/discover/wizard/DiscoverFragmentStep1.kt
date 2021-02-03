package com.ricardojrsousa.movook.presentation.discover.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ricardojrsousa.movook.R

/**
 * Created by Ricardo Sousa on 21/01/2021.
 */
class DiscoverFragmentStep1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.discover_fragment_1, container, false)
    }
}