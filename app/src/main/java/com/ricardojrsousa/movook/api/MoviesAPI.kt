package com.ricardojrsousa.movook.api

import com.ricardojrsousa.movook.BuildConfig
import com.ricardojrsousa.movook.model.UpcomingMovie
import com.ricardojrsousa.movook.model.UpcomingMovieWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ricardosousa on 12/03/2020
 */
interface MoviesAPI {

    @GET("movie/upcoming?api_key=${BuildConfig.MOVIE_DB_API_KEY}&language=en-US")
    fun getUpcomingMovies(@Query("page") page: Int): Single<UpcomingMovieWrapper>
}