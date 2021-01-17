package com.ricardojrsousa.movook.core.repository

import com.ricardojrsousa.movook.core.data.GenreWrapper
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.core.data.MovieDetails
import com.ricardojrsousa.movook.core.data.MovieWrapper

/**
 * Created by ricardosousa on 18/05/2020
 */
interface MoviesDataSource {
    suspend fun getMoviesInTheatres(page: Int): MovieWrapper
    suspend fun getMovieDetails(movieId: String): MovieDetails
    suspend fun getSimilarMovies(movieId: String, page: Int): MovieWrapper
    suspend fun getPersonDetails(personId: String): Person
    suspend fun getTopRatedMovies(page: Int): MovieWrapper
    suspend fun getTopRatedMoviesBackdrops(): String
    suspend fun getGenresList(): GenreWrapper
}