package com.ricardojrsousa.movook.core.data

data class Volume(
    val authors: List<String>,
    val title: String,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val industryIdentifiers: List<IndustryIdentifier>,
    val pageCount: Int,
    val categories: List<String>,
    val averageRating: Double,
    val ratingsCount: Int,
    val imageLinks: BookImageLinks,
    val language: String
)