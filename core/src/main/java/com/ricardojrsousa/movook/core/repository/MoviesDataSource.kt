package com.ricardojrsousa.movook.core.repository

import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.MovieDetails

/**
 * Created by ricardosousa on 18/05/2020
 */
interface MoviesDataSource {
    suspend fun getMoviesInTheatres(page: Int): List<Movie>
    suspend fun getMovieDetails(movieId: String): MovieDetails
    suspend fun getSimilarMovies(movieId: String, page: Int): List<Movie>
    suspend fun getPersonDetails(personId: String): Person
}