package com.ricardojrsousa.movook.framework.di

import com.ricardojrsousa.movook.core.repository.BooksRepository
import com.ricardojrsousa.movook.core.repository.MoviesRepository
import com.ricardojrsousa.movook.core.usecase.*
import com.ricardojrsousa.movook.framework.BookUseCases
import com.ricardojrsousa.movook.framework.MovieUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun getMovieUseCases(moviesRepository: MoviesRepository): MovieUseCases = MovieUseCases(
        GetMoviesInTheatres(moviesRepository),
        GetMovieDetails(moviesRepository),
        GetSimilarMovies(moviesRepository),
        GetPersonDetails(moviesRepository),
        GetTopRatedMovies(moviesRepository),
        GetTopRatedMoviesBackdrops(moviesRepository)
    )

    @Provides
    @Singleton
    fun getBookUseCases(booksRepository: BooksRepository): BookUseCases = BookUseCases(
        SearchBooksByTitle(booksRepository),
        GetBookDetails(booksRepository)
    )
}