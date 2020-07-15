package com.ricardojrsousa.movook.framework

import com.ricardojrsousa.movook.core.usecase.GetMovieDetails
import com.ricardojrsousa.movook.core.usecase.GetSimilarMovies
import com.ricardojrsousa.movook.core.usecase.GetMoviesInTheatres
import com.ricardojrsousa.movook.core.usecase.GetPersonDetails

/**
 * Created by ricardosousa on 25/05/2020
 */
data class MovieUseCases(
    val getMoviesInTheatres: GetMoviesInTheatres,
    val getMovieDetails: GetMovieDetails,
    val getSimilarMovies: GetSimilarMovies,
    val getPersonDetails: GetPersonDetails
)