package com.ricardojrsousa.movook.presentation.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(@Assisted private val savedStateHandle: SavedStateHandle, movieUseCases: MovieUseCases) : BaseViewModel() {

    private val _moviesInTheatres: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesInTheatres: LiveData<List<Movie>> = _moviesInTheatres

    init {
        coroutineScope.launch {
            val list = movieUseCases.getMoviesInTheatres.invoke(1)
            _moviesInTheatres.postValue(list)
        }
    }
}
