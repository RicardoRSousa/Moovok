package com.ricardojrsousa.movook.framework

import com.ricardojrsousa.movook.core.usecase.GetBookDetails
import com.ricardojrsousa.movook.core.usecase.SearchBooksByTitle

/**
 * Created by ricardosousa on 25/05/2020
 */
data class BookUseCases(
    val searchBooksByTitle: SearchBooksByTitle,
    val getBookDetails: GetBookDetails
)