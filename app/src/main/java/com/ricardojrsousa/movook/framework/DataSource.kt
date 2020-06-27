package com.ricardojrsousa.movook.framework

import android.content.Context
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.repository.MoviesDataSource
import com.ricardojrsousa.movook.framework.api.MoviesClient
import com.ricardojrsousa.movook.framework.db.DatabaseService
import com.ricardojrsousa.movook.framework.db.MovieEntity

/**
 * Created by ricardosousa on 25/05/2020
 */
class DataSource(context: Context) : MoviesDataSource {

    private val movieDao = DatabaseService.getInstance(context).movieDao()
    private val movieService = MoviesClient.apiService

    override suspend fun getUpcomingMovies(page: Int): List<Movie> {
        val movies = movieService.getUpcomingMovies(page).results
        movies.forEach { movieDao.addMovieEntity(MovieEntity.fromMovie(it)) }
        return movies!!
    }
}