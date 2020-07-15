package com.ricardojrsousa.movook.framework.api

import com.ricardojrsousa.movook.core.data.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by ricardosousa on 12/03/2020
 */

private const val MOVIE = "movie"
private const val PERSON = "person"

interface MoviesAPI {
    @GET("${MOVIE}/now_playing")
    suspend fun getMoviesInTheatres(@Query("page") page: Int): MovieWrapper

    @GET("${MOVIE}/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetails

    @GET("${MOVIE}/{movie_id}/credits")
    suspend fun getMovieCast(@Path("movie_id") movieId: Int): Cast

    @GET("${MOVIE}/{movie_id}/keywords")
    suspend fun getMovieKeywords(@Path("movie_id") movieId: Int): MovieKeywordWrapper

    @GET("${MOVIE}/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int, @Query("page") page: Int): MovieWrapper

    @GET("${PERSON}/{person_id}")
    suspend fun getPersonDetails(@Path("person_id") personId: Int): Person

    @GET("${PERSON}/{person_id}/movie_credits")
    suspend fun getPersonMovieCredits(@Path("person_id") personId: Int): Credit

}