package com.ricardojrsousa.movook.core.repository

/**
 * Created by ricardosousa on 19/03/2020
 */
class MoviesRepository(private val dataSource: MoviesDataSource) {
    suspend fun getMoviesInTheatres(page: Int) = dataSource.getMoviesInTheatres(page)
    suspend fun getMovieDetails(movieId: Int) = dataSource.getMovieDetails(movieId)
    suspend fun getSimilarMovies(movieId: Int, page: Int) = dataSource.getSimilarMovies(movieId, page)
    suspend fun getPersonDetails(personId: Int) = dataSource.getPersonDetails(personId)
}