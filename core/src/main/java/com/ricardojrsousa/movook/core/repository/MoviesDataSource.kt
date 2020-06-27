package com.ricardojrsousa.movook.core.repository

import com.ricardojrsousa.movook.core.data.Movie

/**
 * Created by ricardosousa on 18/05/2020
 */
interface MoviesDataSource {
    suspend fun getUpcomingMovies(page: Int): List<Movie>
}