package com.ricardojrsousa.movook.core.usecase

import com.ricardojrsousa.movook.core.repository.MoviesRepository

class GetPersonDetails(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(personId: String) = moviesRepository.getPersonDetails(personId)
}