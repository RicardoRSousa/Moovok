package com.ricardojrsousa.movook.framework.api

import com.ricardojrsousa.movook.core.data.BookWrapper
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
interface BooksAPI {
    @GET("volumes")
    suspend fun searchBooksByTitle(@Query("q") query: String): BookWrapper

}