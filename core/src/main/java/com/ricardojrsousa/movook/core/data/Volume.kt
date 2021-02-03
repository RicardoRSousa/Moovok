package com.ricardojrsousa.movook.core.data

private const val SEPARATOR = ", "

class Volume(
    private val authors: List<String>?,
    val title: String,
    val publisher: String,
    val publishedDate: String,
    val description: String?,
    private val industryIdentifiers: List<IndustryIdentifier>?,
    val pageCount: Int,
    private val categories: List<String>?,
    val averageRating: Double,
    val ratingsCount: Int,
    val imageLinks: BookImageLinks?,
    val language: String
) {

    fun getAuthors(): String = this.authors?.joinToString(SEPARATOR) ?: ""
    fun getIsbn13(): String = this.industryIdentifiers?.find { it.type == "ISBN_13" }?.identifier ?: ""
    fun getCategories(): String = this.categories?.joinToString(SEPARATOR) ?: ""

}