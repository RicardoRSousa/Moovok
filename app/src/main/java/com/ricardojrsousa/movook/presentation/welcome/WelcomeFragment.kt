package com.ricardojrsousa.movook.presentation.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ricardojrsousa.movook.R
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.android.synthetic.main.welcome_step_2.*

/**
 * Created by Ricardo Sousa on 20/10/2020
 */
class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        welcome_coordinator.addPage(R.layout.welcome_step_1, R.layout.welcome_step_2)

        close_welcome_btn.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToMainFragment())
        }
    }
}