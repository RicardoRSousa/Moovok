package com.ricardojrsousa.movook.presentation.popularmovies

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.MovieWrapper
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import com.ricardojrsousa.movook.utils.filterAdult
import kotlinx.coroutines.launch


class PopularMoviesViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val movieUseCases: MovieUseCases
) : BaseViewModel() {

    private val _popularMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val popularMovies: LiveData<List<Movie>> = _popularMovies

    fun init() {
        coroutineScope.launch {
            val movieWrapper = movieUseCases.getPopularMovies.invoke()
            _popularMovies.postValue(movieWrapper.results.filterAdult())
        }
    }
}
