package com.ricardojrsousa.movook.core.repository

import com.ricardojrsousa.movook.core.data.*

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
    suspend fun getDiscoverMovies(includedGenres: List<String>, fromYear: Int, toYear: Int, minRuntime: Int, maxRuntime: Int, minVoteAvg: Double, page: Int): MovieWrapper
    suspend fun getMovieVideos(movieId: String): MovieVideoWrapper
}