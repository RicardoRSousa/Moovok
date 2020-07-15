package com.ricardojrsousa.movook.presentation.persondetails

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.framework.di.DaggerPersonDetailsViewModelComponent
import com.ricardojrsousa.movook.framework.di.RepositoryModule
import javax.inject.Inject

class PersonDetailsViewModelFactory(
    private val applicationContext: Context,
    private val personId: Int
) : ViewModelProvider.Factory {

    @Inject
    lateinit var movieUseCases: MovieUseCases


    init {
        DaggerPersonDetailsViewModelComponent.builder()
            .repositoryModule(RepositoryModule(applicationContext))
            .build().inject(this)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(PersonDetailsViewModel::class.java) ->
                return PersonDetailsViewModel(movieUseCases, personId) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}