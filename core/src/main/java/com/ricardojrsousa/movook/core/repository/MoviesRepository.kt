package com.ricardojrsousa.movook.core.repository

/**
 * Created by ricardosousa on 19/03/2020
 */
class MoviesRepository(private val dataSource: MoviesDataSource) {
    suspend fun getMoviesInTheatres(page: Int) = dataSource.getMoviesInTheatres(page)
    suspend fun getMovieDetails(movieId: String) = dataSource.getMovieDetails(movieId)
    suspend fun getSimilarMovies(movieId: String, page: Int) = dataSource.getSimilarMovies(movieId, page)
    suspend fun getPersonDetails(personId: String) = dataSource.getPersonDetails(personId)
    suspend fun getTopRatedMovies(page: Int) = dataSource.getTopRatedMovies(page)
    suspend fun getTopRatedMoviesBackdrops() = dataSource.getTopRatedMoviesBackdrops()
    suspend fun getGenresList() = dataSource.getGenresList()
}