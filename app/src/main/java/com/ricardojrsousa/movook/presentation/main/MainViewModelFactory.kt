package com.ricardojrsousa.movook.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ricardojrsousa.movook.presentation.main.MainViewModel
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.framework.di.DaggerMainViewModelComponent
import com.ricardojrsousa.movook.framework.di.RepositoryModule
import javax.inject.Inject

/**
 * Created by ricardosousa on 19/03/2020
 */
class MainViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {

    @Inject
    lateinit var movieUseCases: MovieUseCases

    init {
        DaggerMainViewModelComponent.builder()
            .repositoryModule(RepositoryModule(applicationContext))
            .build().inject(this)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                return MainViewModel(movieUseCases) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}