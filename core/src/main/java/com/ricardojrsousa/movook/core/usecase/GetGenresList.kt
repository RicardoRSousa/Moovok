package com.ricardojrsousa.movook.core.usecase

import com.ricardojrsousa.movook.core.repository.MoviesRepository

/**
 * Created by Ricardo Sousa on 07/12/2020
 */
class GetGenresList(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke() = moviesRepository.getGenresList()
}