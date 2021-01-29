package com.ricardojrsousa.movook.core.usecase

import com.ricardojrsousa.movook.core.repository.MoviesRepository

class GetDiscoverMovies(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(includedGenres: List<String>, fromYear: Int, toYear: Int, minRuntime: Int, maxRuntime: Int, minVoteAvg: Double, page: Int) =
        moviesRepository.getDiscoverMovies(includedGenres, fromYear, toYear, minRuntime, maxRuntime, minVoteAvg, page)
}