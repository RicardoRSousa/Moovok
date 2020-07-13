package com.ricardojrsousa.movook.framework

import com.ricardojrsousa.movook.core.usecase.GetMovieDetails
import com.ricardojrsousa.movook.core.usecase.GetSimilarMovies
import com.ricardojrsousa.movook.core.usecase.GetUpcomingMovies
import com.ricardojrsousa.movook.core.usecase.SearchBooksByTitle

/**
 * Created by ricardosousa on 25/05/2020
 */
data class MovieUseCases(
    val getUpcomingMovies: GetUpcomingMovies,
    val getMovieDetails: GetMovieDetails,
    val getSimilarMovies: GetSimilarMovies
)