package com.ricardojrsousa.movook.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ricardojrsousa.movook.presentation.MainViewModel
import com.ricardojrsousa.movook.framework.UseCases
import com.ricardojrsousa.movook.framework.di.DaggerMainViewModelComponent
import com.ricardojrsousa.movook.framework.di.DaggerMovieDetailsViewModelComponent
import com.ricardojrsousa.movook.framework.di.RepositoryModule
import com.ricardojrsousa.movook.presentation.details.MovieDetailsViewModel
import javax.inject.Inject

/**
 * Created by ricardosousa on 19/03/2020
 */
class MovieDetailsViewModelFactory(
    private val applicationContext: Context,
    private val movieId: Int
) : ViewModelProvider.Factory {

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerMovieDetailsViewModelComponent.builder()
            .repositoryModule(RepositoryModule(applicationContext))
            .build().inject(this)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieDetailsViewModel::class.java) ->
                return MovieDetailsViewModel(useCases, movieId) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}