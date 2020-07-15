package com.ricardojrsousa.movook.framework

import android.content.Context
import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.MovieDetails
import com.ricardojrsousa.movook.core.data.Person
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

    override suspend fun getMoviesInTheatres(page: Int): List<Movie> {
        val movies = movieService.getMoviesInTheatres(page).results
        movies.forEach { movieDao.addMovieEntity(MovieEntity.fromMovie(it)) }
        return movies!!
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val credits = movieService.getMovieCast(movieId).cast
        val basedOnBook = movieService.getMovieKeywords(movieId).keywords.any { it.id == 818 || it.id == 3096 || it.id == 246466 || it.name.contains("book") || it.name.contains("novel") }
        val movieDetails = movieService.getMovieDetails(movieId)
        movieDetails.credits = credits
        movieDetails.basedOnBook = basedOnBook
        return movieDetails
    }

    override suspend fun getSimilarMovies(movieId: Int, page: Int): List<Movie> {
        val movies = movieService.getSimilarMovies(movieId, page).results
        movies.forEach { movieDao.addMovieEntity(MovieEntity.fromMovie(it)) }
        return movies!!
    }

    override suspend fun getPersonDetails(personId: Int): Person {
        val personDetails = movieService.getPersonDetails(personId)
        val personCredits = movieService.getPersonMovieCredits(personId)
        personDetails.credits = personCredits.apply { this.movies = this.movies.sortedByDescending { it.voteAverage } }
        return personDetails
    }

    override suspend fun searchBooksByTitle(query: String): List<Book> {
        val books = bookService.searchBooksByTitle(query)
        //TODO: Save on db
        return books.items
    }
}