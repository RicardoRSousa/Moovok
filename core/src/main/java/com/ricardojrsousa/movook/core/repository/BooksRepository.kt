package com.ricardojrsousa.movook.core.repository

/**
 * Created by ricardosousa on 19/03/2020
 */
class BooksRepository(private val dataSource: BooksDataSource) {
    suspend fun searchBooksByTitle(title: String) = dataSource.searchBooksByTitle(title)
    suspend fun getBookDetails(id: String) = dataSource.getBookDetails(id)
}