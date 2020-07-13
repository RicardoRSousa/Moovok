package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName

/**
 * Created by ricardosousa on 19/03/2020
 */

data class MovieWrapper(
    val results: List<Movie>,
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)