package com.ricardojrsousa.movook.presentation.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import com.ricardojrsousa.movook.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(@Assisted private val savedStateHandle: SavedStateHandle, private val movieUseCases: MovieUseCases) : BaseViewModel() {

    private val _moviesInTheatres: SingleLiveEvent<List<Movie>> = SingleLiveEvent()
    val moviesInTheatres: LiveData<List<Movie>> = _moviesInTheatres

    private var nowPlayingMoviesCurrentPage = 1
    private var nowPlayingMoviesTotalPages = 0
    private var isLoading = false

    private val _loadedItems: MutableList<Movie> = mutableListOf()
    val loadedItems: List<Movie> = _loadedItems

    private val _topRatedMovieBackdrop: MutableLiveData<String?> = MutableLiveData()
    val topRatedMovieBackdrop: LiveData<String?> = _topRatedMovieBackdrop

    init {
        getMoviesInTheatres(nowPlayingMoviesCurrentPage)
        getRandomBackdrop()
    }

    private fun getMoviesInTheatres(page: Int) {
        coroutineScope.launch {
            val wrapper = movieUseCases.getMoviesInTheatres.invoke(page)

            nowPlayingMoviesTotalPages = wrapper.totalPages

            _moviesInTheatres.postValue(wrapper.results)
            _loadedItems.addAll(wrapper.results)
            isLoading = false
        }
    }

    fun loadNextPageOfNowPlayingMovies() {
        isLoading = true
        nowPlayingMoviesCurrentPage += 1
        getMoviesInTheatres(nowPlayingMoviesCurrentPage)
    }

    fun isLastPage() = nowPlayingMoviesCurrentPage == nowPlayingMoviesTotalPages

    fun isLoading() = isLoading

    private fun getRandomBackdrop() {
        coroutineScope.launch {
            val image = movieUseCases.getTopRatedMoviesBackdrops.invoke()
            _topRatedMovieBackdrop.postValue(image)
        }
    }
}
