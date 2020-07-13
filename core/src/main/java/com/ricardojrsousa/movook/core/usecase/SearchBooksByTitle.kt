package com.ricardojrsousa.movook.core.usecase

import com.ricardojrsousa.movook.core.repository.BooksRepository

/**
 * Created by ricardosousa on 21/05/2020
 */
class SearchBooksByTitle(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(title: String) = booksRepository.searchBooksByTitle("intitle:{$title}")
}