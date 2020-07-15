package com.ricardojrsousa.movook.presentation.persondetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ricardojrsousa.movook.core.data.MovieDetails
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import kotlinx.coroutines.launch

class PersonDetailsViewModel(private val movieUseCases: MovieUseCases, personId: Int) : BaseViewModel() {

    private val _personDetails: MutableLiveData<Person> = MutableLiveData()
    val personDetails: LiveData<Person> = _personDetails

    init {
        coroutineScope.launch {
            val personDetails = movieUseCases.getPersonDetails(personId)
            _personDetails.postValue(personDetails)
        }
    }

}
