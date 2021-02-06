package com.ricardojrsousa.movook.core.usecase

import com.ricardojrsousa.movook.core.repository.MoviesRepository

/**
 * Created by Ricardo Sousa on 06/02/2021.
 */
class GetMovieVideos(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movieId: String) = moviesRepository.getMovieVideos(movieId)
}