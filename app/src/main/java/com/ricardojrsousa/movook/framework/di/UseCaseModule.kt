package com.ricardojrsousa.movook.framework.di

import com.ricardojrsousa.movook.core.repository.MoviesRepository
import com.ricardojrsousa.movook.core.usecase.GetUpcomingMovies
import com.ricardojrsousa.movook.framework.UseCases
import dagger.Module
import dagger.Provides

/**
 * Created by ricardosousa on 27/05/2020
 */
@Module
class UseCaseModule {

    @Provides
    fun getUseCases(repository: MoviesRepository) = UseCases(GetUpcomingMovies(repository))
}