package com.ricardojrsousa.movook.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ricardosousa on 19/03/2020
 */

data class UpcomingMovieWrapper(
    val results: List<UpcomingMovie>,
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)