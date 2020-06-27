package com.ricardojrsousa.movook.framework

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ricardojrsousa.movook.core.data.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(useCases: UseCases) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("Exception HANDLER", throwable.localizedMessage)
    }
    private val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandler)

    private val _movieName: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieName: LiveData<List<Movie>> = _movieName

    init {
       coroutineScope.launch {
            val list = useCases.getUpcomingMovies.invoke(1)
            _movieName.postValue(list)
        }
    }

}
