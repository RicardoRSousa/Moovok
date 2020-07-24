package com.ricardojrsousa.movook.framework.di

import android.content.Context
import com.ricardojrsousa.movook.core.repository.BooksRepository
import com.ricardojrsousa.movook.core.repository.MoviesRepository
import com.ricardojrsousa.movook.framework.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(@ApplicationContext context: Context) = MoviesRepository(DataSource(context))

    @Provides
    @Singleton
    fun provideBookRepository(@ApplicationContext context: Context) = BooksRepository(DataSource(context))
}