package com.ricardojrsousa.movook.presentation.persondetails

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import kotlinx.coroutines.launch

class PersonDetailsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val movieUseCases: MovieUseCases
) : BaseViewModel() {

    private val _personDetails: MutableLiveData<Person> = MutableLiveData()
    val personDetails: LiveData<Person> = _personDetails

    fun init(personId: String) {
        coroutineScope.launch {
            val personDetails = movieUseCases.getPersonDetails(personId)
            _personDetails.postValue(personDetails)
        }
    }

}
