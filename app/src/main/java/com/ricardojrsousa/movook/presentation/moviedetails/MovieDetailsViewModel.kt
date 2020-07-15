package com.ricardojrsousa.movook.presentation.moviedetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.MovieDetails
import com.ricardojrsousa.movook.framework.BookUseCases
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieUseCases: MovieUseCases, private val bookUseCases: BookUseCases, private val movieId: Int) : BaseViewModel() {

    private val _movieDetails: MutableLiveData<MovieDetails> = MutableLiveData()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _similarMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val similarMovies: LiveData<List<Movie>> = _similarMovies

    private val _relatedBooks: MutableLiveData<List<Book>> = MutableLiveData()
    val relatedBooks: LiveData<List<Book>> = _relatedBooks

    init {
        coroutineScope.launch {
            val movieDetails = movieUseCases.getMovieDetails.invoke(movieId)
            _movieDetails.postValue(movieDetails)

            val similarMovies = movieUseCases.getSimilarMovies.invoke(movieId, 1)
            _similarMovies.postValue(similarMovies)

            //TODO: This logic should stay or should go?
            if (movieDetails.basedOnBook) {
                getRelatedBooks(movieDetails.title)
            }
        }
    }

    fun getRelatedBooks(movieName: String?) {
        coroutineScope.launch {
            if (!movieName.isNullOrBlank()) {
                val relatedBooks = bookUseCases.searchBooksByTitle.invoke(movieName)
                _relatedBooks.postValue(relatedBooks)
            }
        }
    }

}
