package com.ricardojrsousa.movook.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.ricardojrsousa.movook.presentation.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    abstract val viewModel: T
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        navController = findNavController()
    }

    fun navigate(action: NavDirections, extras: FragmentNavigator.Extras) {
        navController.navigate(action, extras)
    }
}