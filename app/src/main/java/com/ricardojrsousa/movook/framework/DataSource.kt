package com.ricardojrsousa.movook.framework

import android.content.Context
import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.MovieDetails
import com.ricardojrsousa.movook.core.repository.BooksDataSource
import com.ricardojrsousa.movook.core.repository.MoviesDataSource
import com.ricardojrsousa.movook.framework.api.BooksClient
import com.ricardojrsousa.movook.framework.api.MoviesClient
import com.ricardojrsousa.movook.framework.db.DatabaseService
import com.ricardojrsousa.movook.framework.db.MovieEntity

/**
 * Created by ricardosousa on 25/05/2020
 */
class DataSource(context: Context) : MoviesDataSource, BooksDataSource {

    private val movieDao = DatabaseService.getInstance(context).movieDao()
    private val movieService = MoviesClient.apiService

    private val bookService = BooksClient.apiService

    override suspend fun getUpcomingMovies(page: Int): List<Movie> {
        val movies = movieService.getUpcomingMovies(page).results
        movies.forEach { movieDao.addMovieEntity(MovieEntity.fromMovie(it)) }
        return movies!!
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return movieService.getMovieDetails(movieId)
    }

    override suspend fun getSimilarMovies(movieId: Int, page: Int): List<Movie> {
        val movies = movieService.getSimilarMovies(movieId, page).results
        movies.forEach { movieDao.addMovieEntity(MovieEntity.fromMovie(it)) }
        return movies!!
    }

    override suspend fun searchBooksByTitle(query: String): List<Book> {
        val books = bookService.searchBooksByTitle(query)
        //TODO: Save on db
        return books.items
    }
}