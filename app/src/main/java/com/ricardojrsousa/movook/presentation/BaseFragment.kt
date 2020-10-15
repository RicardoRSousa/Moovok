package com.ricardojrsousa.movook.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.ricardojrsousa.movook.R

abstract class BaseFragment<T : BaseViewModel>(private val contentLayout: Int) : Fragment() {

    private lateinit var rootView: FrameLayout
    abstract val viewModel: T
    lateinit var navController: NavController
    lateinit var loadingView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
    }

    fun navigate(action: NavDirections, extras: FragmentNavigator.Extras) {
        navController.navigate(action, extras)
    }

    fun navigate(action: NavDirections) {
        navController.navigate(action)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_base, container, false) as FrameLayout
        loadingView = inflater.inflate(R.layout.fragment_loading, rootView, false)

        postponeEnterTransition()

        val contentView = inflater.inflate(contentLayout, rootView, false)
        rootView.addView(contentView)
        return rootView
    }

    fun showLoading() {
        hideLoading()
        rootView.addView(loadingView)
    }

    fun hideLoading() {
        rootView.removeView(loadingView)
    }
}