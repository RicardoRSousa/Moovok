package com.ricardojrsousa.movook.framework.di

import com.ricardojrsousa.movook.core.repository.BooksRepository
import com.ricardojrsousa.movook.core.repository.MoviesRepository
import com.ricardojrsousa.movook.core.usecase.GetMovieDetails
import com.ricardojrsousa.movook.core.usecase.GetSimilarMovies
import com.ricardojrsousa.movook.core.usecase.GetUpcomingMovies
import com.ricardojrsousa.movook.core.usecase.SearchBooksByTitle
import com.ricardojrsousa.movook.framework.BookUseCases
import com.ricardojrsousa.movook.framework.MovieUseCases
import dagger.Module
import dagger.Provides

/**
 * Created by ricardosousa on 27/05/2020
 */
@Module
class UseCaseModule {

    @Provides
    fun getMovieUseCases(moviesRepository: MoviesRepository) = MovieUseCases(
        GetUpcomingMovies(moviesRepository),
        GetMovieDetails(moviesRepository),
        GetSimilarMovies(moviesRepository)
    )

    @Provides
    fun getBookUseCases(booksRepository: BooksRepository) = BookUseCases(
        SearchBooksByTitle(booksRepository)
    )
}