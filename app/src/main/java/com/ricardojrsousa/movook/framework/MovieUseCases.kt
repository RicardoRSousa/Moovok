package com.ricardojrsousa.movook.framework

import com.ricardojrsousa.movook.core.usecase.*

/**
 * Created by ricardosousa on 25/05/2020
 */
data class MovieUseCases(
    val getMoviesInTheatres: GetMoviesInTheatres,
    val getMovieDetails: GetMovieDetails,
    val getSimilarMovies: GetSimilarMovies,
    val getPersonDetails: GetPersonDetails,
    val getTopRatedMovies: GetTopRatedMovies,
    val getTopRatedMoviesBackdrops: GetTopRatedMoviesBackdrops,
    val getGenresList: GetGenresList,
)