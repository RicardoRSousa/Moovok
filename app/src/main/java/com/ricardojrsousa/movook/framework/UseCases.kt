package com.ricardojrsousa.movook.framework

import com.ricardojrsousa.movook.core.usecase.GetMovieDetails
import com.ricardojrsousa.movook.core.usecase.GetUpcomingMovies

/**
 * Created by ricardosousa on 25/05/2020
 */
data class UseCases(
    val getUpcomingMovies: GetUpcomingMovies,
    val getMovieDetails: GetMovieDetails
)