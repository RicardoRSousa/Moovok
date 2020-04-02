package com.ricardojrsousa.movook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ricardojrsousa.movook.model.UpcomingMovie
import com.ricardojrsousa.movook.repository.MoviesRepository

class MainViewModel(repository: MoviesRepository) : ViewModel() {

    private val _movieName: MutableLiveData<UpcomingMovie> = MutableLiveData()
    val movieName: LiveData<UpcomingMovie> = _movieName

    init {
        repository.getUpcomingMovies(1)
            .subscribe { t1 -> _movieName.postValue(t1.get(0)) }
    }

}
