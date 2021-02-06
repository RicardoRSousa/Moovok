package com.ricardojrsousa.movook.presentation.moviedetails

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.MovieDetails
import com.ricardojrsousa.movook.framework.BookUseCases
import com.ricardojrsousa.movook.framework.MovieUseCases
import com.ricardojrsousa.movook.presentation.BaseViewModel
import com.ricardojrsousa.movook.utils.filterAdult
import kotlinx.coroutines.launch

class MovieDetailsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    val movieUseCases: MovieUseCases, private val bookUseCases: BookUseCases
) : BaseViewModel() {

    private val _movieDetails: MutableLiveData<MovieDetails> = MutableLiveData()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _similarMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val similarMovies: LiveData<List<Movie>> = _similarMovies

    private val _relatedBooks: MutableLiveData<List<Book>> = MutableLiveData()
    val relatedBooks: LiveData<List<Book>> = _relatedBooks

    private val _movieTrailer: MutableLiveData<String> = MutableLiveData()
    val movieTrailer: LiveData<String> = _movieTrailer

    fun init(movieId: String) {
        coroutineScope.launch {
            val movieDetails = movieUseCases.getMovieDetails.invoke(movieId)
            _movieDetails.postValue(movieDetails)

            val similarMovies = movieUseCases.getSimilarMovies.invoke(movieId, 1)
            _similarMovies.postValue(similarMovies.results.filterAdult())

            val movieVideos = movieUseCases.getMovieVideos.invoke(movieId)
            _movieTrailer.postValue(movieVideos.getYoutubeTrailerKey())

            //TODO: This logic should stay or should go?
            if (movieDetails.isBasedOnBook()) {
                getRelatedBooks(movieDetails.title)
            }
        }
    }

    private fun getRelatedBooks(movieName: String?) {
        coroutineScope.launch {
            if (!movieName.isNullOrBlank()) {
                val relatedBooks = bookUseCases.searchBooksByTitle.invoke(movieName)
                _relatedBooks.postValue(relatedBooks)
            }
        }
    }

}
