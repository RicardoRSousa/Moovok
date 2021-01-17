package com.ricardojrsousa.movook.presentation.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Visibility
import com.ricardojrsousa.movook.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val FIRST_APP_OPENING = "first_app_opening"
    private val PREFERENCES = "preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment_container)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.welcomeFragment, R.id.mainFragment))
        toolbar.setupWithNavController(navController, appBarConfiguration)
        toolbar.bringToFront()

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination != controller.graph.findNode(R.id.welcomeFragment)) {
                toolbar.visibility = View.VISIBLE
            } else {
                toolbar.visibility = View.INVISIBLE
            }
        }

        val sharedPref = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE) ?: return
        val firstTimeUser = sharedPref.getBoolean(FIRST_APP_OPENING, true)

        if (firstTimeUser) {
            with(sharedPref.edit()) {
                putBoolean(FIRST_APP_OPENING, false)
                apply()
            }
            navController.navigate(R.id.welcomeFragment)
        }
    }
}
