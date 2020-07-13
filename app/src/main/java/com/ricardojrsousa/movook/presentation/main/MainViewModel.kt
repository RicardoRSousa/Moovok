package com.ricardojrsousa.movook.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.framework.MovieUseCases
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val movieUseCases: MovieUseCases) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("Exception HANDLER", throwable.localizedMessage)
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandler)

    private val _upcomingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val upcomingMovies: LiveData<List<Movie>> = _upcomingMovies

    init {
        coroutineScope.launch {
            val list = movieUseCases.getUpcomingMovies.invoke(1)
            _upcomingMovies.postValue(list)
        }
    }
}
