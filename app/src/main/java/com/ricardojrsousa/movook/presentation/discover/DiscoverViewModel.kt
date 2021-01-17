package com.ricardojrsousa.movook.presentation.discover

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.core.data.Genre
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Ricardo Sousa on 07/12/2020
 */
class DiscoverViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val movieUseCases: MovieUseCases
) : BaseViewModel() {

    private val _genresList: MutableLiveData<List<Genre>> = MutableLiveData()
    val genres = _genresList

    fun init() {
        coroutineScope.launch {
            val genres = movieUseCases.getGenresList.invoke()
            _genresList.postValue(genres.genres)
        }
    }

}