package com.ricardojrsousa.movook.core.usecase

import com.ricardojrsousa.movook.core.repository.MoviesRepository

/**
 * Created by Ricardo Sousa on 14/10/2020
 */
class GetPopularMovies (private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke() = moviesRepository.getPopularMovies()
}