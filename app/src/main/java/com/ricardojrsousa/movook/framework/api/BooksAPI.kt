package com.ricardojrsousa.movook.framework.api

import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.core.data.BookWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by ricardosousa on 12/03/2020
 */
interface BooksAPI {
    @GET("volumes?printType=books")
    suspend fun searchBooksByTitle(@Query("q") query: String): BookWrapper

    @GET("volumes/{id}")
    suspend fun getBookById(@Path("id") id: String): Book

}