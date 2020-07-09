package com.ricardojrsousa.movook.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ricardojrsousa.movook.presentation.MainViewModel
import com.ricardojrsousa.movook.framework.UseCases
import com.ricardojrsousa.movook.framework.di.DaggerMainViewModelComponent
import com.ricardojrsousa.movook.framework.di.RepositoryModule
import javax.inject.Inject

/**
 * Created by ricardosousa on 19/03/2020
 */
class MainViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerMainViewModelComponent.builder()
            .repositoryModule(RepositoryModule(applicationContext))
            .build().inject(this)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                return MainViewModel(
                    useCases
                ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}