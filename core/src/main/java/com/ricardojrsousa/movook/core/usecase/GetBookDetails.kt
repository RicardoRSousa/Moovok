package com.ricardojrsousa.movook.core.usecase

import com.ricardojrsousa.movook.core.repository.BooksRepository

class GetBookDetails(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(id: String) = booksRepository.getBookDetails(id)
}