package com.ricardojrsousa.movook.presentation.topratedmovies

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import com.ricardojrsousa.movook.utils.SingleLiveEvent
import com.ricardojrsousa.movook.utils.filterAdult
import com.ricardojrsousa.movook.utils.filterByVoteCount
import kotlinx.coroutines.launch


class TopRatedMoviesViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val movieUseCases: MovieUseCases
) : BaseViewModel() {

    private val minVoteCount = 1000

    private var topRatedMoviesCurrentPage = 1
    private var topRatedgMoviesTotalPages = 0
    private var isLoading = false

    private val _loadedItems: MutableList<Movie> = mutableListOf()
    val loadedItems: List<Movie> = _loadedItems

    private val _topRatedMovies: SingleLiveEvent<List<Movie>> = SingleLiveEvent()
    val topRatedMovies: LiveData<List<Movie>> = _topRatedMovies

    fun init() {
        getTopRatedMovies(topRatedMoviesCurrentPage)
    }

    private fun getTopRatedMovies(page: Int){
        coroutineScope.launch {
            val movieWrapper = movieUseCases.getTopRatedMovies.invoke(page)
            val movies = movieWrapper.results.filterAdult().filterByVoteCount(minVoteCount)
            _topRatedMovies.postValue(movies)
            _loadedItems.addAll(movies)


            topRatedgMoviesTotalPages = movieWrapper.totalPages
            isLoading = false
        }
    }

    fun loadNextPageOfTopRatedMovies() {
        isLoading = true
        topRatedMoviesCurrentPage += 1
        getTopRatedMovies(topRatedMoviesCurrentPage)
    }

    fun isLastPage() = topRatedMoviesCurrentPage == topRatedgMoviesTotalPages

    fun isLoading() = isLoading
}
