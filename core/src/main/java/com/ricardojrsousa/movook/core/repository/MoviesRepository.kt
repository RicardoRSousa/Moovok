package com.ricardojrsousa.movook.core.repository

/**
 * Created by ricardosousa on 19/03/2020
 */
class MoviesRepository(private val dataSource: MoviesDataSource) {

    suspend fun getUpcomingMovies(page: Int) = dataSource.getUpcomingMovies(page)
    suspend fun getMovieDetails(movieId: Int) = dataSource.getMovieDetails(movieId)
}