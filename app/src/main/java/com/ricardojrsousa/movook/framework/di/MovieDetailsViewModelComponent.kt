package com.ricardojrsousa.movook.framework.di

import com.ricardojrsousa.movook.viewmodel.MainViewModelFactory
import com.ricardojrsousa.movook.viewmodel.MovieDetailsViewModelFactory
import dagger.Component

/**
 * Created by ricardosousa on 27/05/2020
 */
@Component(modules = [RepositoryModule::class, UseCaseModule::class])
interface MovieDetailsViewModelComponent {
    fun inject(movieDetailsViewModelFactory: MovieDetailsViewModelFactory)
}