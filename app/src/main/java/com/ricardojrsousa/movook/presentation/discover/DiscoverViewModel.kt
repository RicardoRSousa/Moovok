package com.ricardojrsousa.movook.presentation.discover

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ricardojrsousa.movook.core.data.Genre
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import com.ricardojrsousa.movook.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by Ricardo Sousa on 07/12/2020
 */
class DiscoverViewModel @ViewModelInject constructor(
    private val movieUseCases: MovieUseCases
) : BaseViewModel() {

    private val _genresList: MutableLiveData<List<Genre>> = MutableLiveData()
    val genres = _genresList

    private val _wizardConclusion: SingleLiveEvent<Unit> = SingleLiveEvent()
    val wizardConclusion = _wizardConclusion

    private val _discoveredMoviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    val discoveredMoviesList: LiveData<List<Movie>> = _discoveredMoviesList

    private val _discoveredMoviesCount: MutableLiveData<Int> = MutableLiveData()
    val discoveredMoviesCount: LiveData<Int> = _discoveredMoviesCount

    private val includedGenresList: ArrayList<String> = ArrayList()

    private var fromYear: Int = 1960
    private var toYear: Int = 2021

    private var minRuntime: Int = 1
    private var maxRuntime: Int = 600

    private var minVoteAvg: Double = 0.0

    private var suggestedMoviesCurrentPage = 0
    private var suggestedMoviesTotalPages = 0
    private var isLoading = false

    fun init() {
        coroutineScope.launch {
            val genres = movieUseCases.getGenresList.invoke()
            _genresList.postValue(genres.genres)
        }
    }

    private fun discoverMovies(page: Int = suggestedMoviesCurrentPage) {
        coroutineScope.launch {
            val discoveredMovies = movieUseCases.getDiscoverMovies.invoke(
                includedGenresList,
                fromYear,
                toYear,
                minRuntime,
                maxRuntime,
                minVoteAvg,
                page,
            )
            _discoveredMoviesCount.postValue(discoveredMovies.totalResults)
            _discoveredMoviesList.postValue(discoveredMovies.results)
            isLoading = false
            suggestedMoviesTotalPages = discoveredMovies.totalPages
        }
    }

    fun loadSuggestions() {
        isLoading = true
        suggestedMoviesCurrentPage += 1
        discoverMovies(suggestedMoviesCurrentPage)
    }

    fun includeGenre(genre: Genre) {
        this.includedGenresList.add(genre.id)
    }

    fun ignoreGenre(genre: Genre) {
        this.includedGenresList.remove(genre.id)
    }

    fun getIncludedGenres(): List<String> {
        return includedGenresList
    }

    fun setYearRange(fromYear: Int, toYear: Int) {
        this.fromYear = fromYear
        this.toYear = toYear
    }

    fun setRangeRuntime(minRuntime: Int, maxRuntime: Int) {
        this.minRuntime = minRuntime
        this.maxRuntime = maxRuntime
    }

    fun setMinVoteAvg(minVoteAvg: Double) {
        this.minVoteAvg = minVoteAvg
    }

    fun finishWizard() {
        _discoveredMoviesList.value = listOf()
        _wizardConclusion.call()
    }

    fun isLastPage() = suggestedMoviesCurrentPage == suggestedMoviesTotalPages

    fun isLoading() = isLoading

    fun resetCurrentSuggestionsPage() {
        suggestedMoviesCurrentPage = 0
    }

}