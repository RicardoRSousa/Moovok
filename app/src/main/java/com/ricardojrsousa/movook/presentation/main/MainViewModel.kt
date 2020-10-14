package com.ricardojrsousa.movook.presentation.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import com.ricardojrsousa.movook.utils.filterAdult
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(@Assisted private val savedStateHandle: SavedStateHandle, private val movieUseCases: MovieUseCases) : BaseViewModel() {

    private val _moviesInTheatres: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesInTheatres: LiveData<List<Movie>> = _moviesInTheatres

    private var nowPlayingMoviesCurrentPage = 1
    private var nowPlayingMoviesTotalPages = 0

    init {
        getMoviesInTheatres(nowPlayingMoviesCurrentPage)
    }

    private fun getMoviesInTheatres(page: Int) {
        coroutineScope.launch {
            val wrapper = movieUseCases.getMoviesInTheatres.invoke(page)

            nowPlayingMoviesCurrentPage = wrapper.page
            nowPlayingMoviesTotalPages = wrapper.totalPages

            _moviesInTheatres.postValue(wrapper.results.filterAdult())
        }
    }

    fun loadNextPageOfNowPlayingMovies() {
        getMoviesInTheatres(nowPlayingMoviesCurrentPage + 1)
    }

    fun isLastPage() = nowPlayingMoviesCurrentPage == nowPlayingMoviesTotalPages
}
