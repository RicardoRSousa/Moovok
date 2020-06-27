package com.ricardojrsousa.movook.framework.api

import com.ricardojrsousa.movook.core.data.MovieWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by ricardosousa on 12/03/2020
 */
interface MoviesAPI {
    @GET("movie/upcoming?api_key=040509eb18929f6db970dba1c4f0f836&language=en-US")
    suspend fun getUpcomingMovies(@Query("page") page: Int): MovieWrapper
}