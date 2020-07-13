package com.ricardojrsousa.movook.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ricardojrsousa.movook.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f

        topbar.bringToFront()
    }

}
