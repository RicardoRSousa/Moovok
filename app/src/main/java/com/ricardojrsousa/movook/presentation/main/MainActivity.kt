package com.ricardojrsousa.movook.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.ricardojrsousa.movook.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        initAdView()

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

    private fun initAdView() {
        MobileAds.initialize(applicationContext)
        val adRequest = AdRequest.Builder().build()
        ad_view.loadAd(adRequest)
    }

    companion object {
        private const val FIRST_APP_OPENING = "first_app_opening"
        private const val PREFERENCES = "preferences"
    }
}
