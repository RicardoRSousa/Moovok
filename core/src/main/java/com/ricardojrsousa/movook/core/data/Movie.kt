package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName

/**
 * Created by ricardosousa on 12/03/2020
 */
open class Movie(
    @SerializedName("poster_path")
    val posterPath: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val title: String,
    val popularity: Double,
    @SerializedName("vote_average")
    val voteAverage: Double
)