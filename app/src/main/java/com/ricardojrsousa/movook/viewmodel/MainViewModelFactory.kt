package com.ricardojrsousa.movook.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ricardojrsousa.movook.api.MoviesClient
import com.ricardojrsousa.movook.repository.MoviesRepository

/**
 * Created by ricardosousa on 19/03/2020
 */
object MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                return MainViewModel(MoviesRepository(MoviesClient.apiService)) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}