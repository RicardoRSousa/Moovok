package com.ricardojrsousa.movook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ricardojrsousa.movook.api.MoviesClient
import com.ricardojrsousa.movook.repository.MoviesRepository
import com.ricardojrsousa.movook.view.MainFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction().add(R.id.container, MainFragment.newInstance()).commit()
    }
}
