package com.ricardojrsousa.movook.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val movieUseCases: MovieUseCases) : BaseViewModel() {

    private val _moviesInTheatres: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesInTheatres: LiveData<List<Movie>> = _moviesInTheatres

    init {
        coroutineScope.launch {
            val list = movieUseCases.getMoviesInTheatres.invoke(1)
            _moviesInTheatres.postValue(list)
        }
    }
}
