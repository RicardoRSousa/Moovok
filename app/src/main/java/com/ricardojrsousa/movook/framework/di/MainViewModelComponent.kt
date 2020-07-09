package com.ricardojrsousa.movook.framework.di

import com.ricardojrsousa.movook.viewmodel.MainViewModelFactory
import dagger.Component

/**
 * Created by ricardosousa on 27/05/2020
 */
@Component(modules = [RepositoryModule::class, UseCaseModule::class])
interface MainViewModelComponent {
    fun inject(mainViewModelFactory: MainViewModelFactory)
}