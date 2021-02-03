package com.ricardojrsousa.movook.core.repository

import com.ricardojrsousa.movook.core.data.Book

/**
 * Created by ricardosousa on 18/05/2020
 */
interface BooksDataSource {
    suspend fun searchBooksByTitle(title: String): List<Book>
    suspend fun getBookDetails(id: String): Book
}