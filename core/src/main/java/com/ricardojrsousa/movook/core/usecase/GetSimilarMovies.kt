package com.ricardojrsousa.movook.core.usecase

import com.ricardojrsousa.movook.core.repository.MoviesRepository

class GetSimilarMovies(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movieId: String, page: Int) = moviesRepository.getSimilarMovies(movieId, page)
}