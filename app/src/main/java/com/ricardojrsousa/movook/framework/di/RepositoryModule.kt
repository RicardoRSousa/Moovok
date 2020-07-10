package com.ricardojrsousa.movook.framework.di

import android.content.Context
import com.ricardojrsousa.movook.core.repository.MoviesRepository
import com.ricardojrsousa.movook.framework.DataSource
import dagger.Module
import dagger.Provides

/**
 * Created by ricardosousa on 27/05/2020
 */
@Module
class RepositoryModule(private val context: Context) {

    @Provides
    fun provideMovieRepository() = MoviesRepository(DataSource(context))
}