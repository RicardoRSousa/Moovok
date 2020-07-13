package com.ricardojrsousa.movook.framework.api

import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.MovieDetails
import com.ricardojrsousa.movook.core.data.MovieWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by ricardosousa on 12/03/2020
 */
interface MoviesAPI {
    @GET("movie/upcoming?api_key=040509eb18929f6db970dba1c4f0f836&language=en-US")
    suspend fun getUpcomingMovies(@Query("page") page: Int): MovieWrapper

    @GET("movie/{movie_id}?api_key=040509eb18929f6db970dba1c4f0f836&language=en-US")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetails

    @GET("movie/{movie_id}/similar?api_key=040509eb18929f6db970dba1c4f0f836&language=en-US")
    suspend fun getSimilarMovies(@Path("movie_id") movieId: Int, @Query("page") page: Int): MovieWrapper
}