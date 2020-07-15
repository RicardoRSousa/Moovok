package com.ricardojrsousa.movook.framework.di

import com.ricardojrsousa.movook.presentation.persondetails.PersonDetailsViewModelFactory
import com.ricardojrsousa.movook.viewmodel.MovieDetailsViewModelFactory
import dagger.Component

@Component(modules = [RepositoryModule::class, UseCaseModule::class])
interface PersonDetailsViewModelComponent {
    fun inject(personDetailsViewModelFactory: PersonDetailsViewModelFactory)
}