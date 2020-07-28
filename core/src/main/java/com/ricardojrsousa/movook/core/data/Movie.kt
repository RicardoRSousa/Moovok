package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by ricardosousa on 12/03/2020
 */
open class Movie(
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    override val id: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val title: String,
    val popularity: Double,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val adult: Boolean
) : Identifiable {

    fun getReleaseDate(): Calendar? {
        if (!releaseDate.isNullOrBlank()) {
            val format = SimpleDateFormat("yyyy-MM-dd")
            val cal = Calendar.getInstance()
            cal.time = format.parse(releaseDate)
            return cal
        }
        return null
    }

    fun getReleaseYear(): String = getReleaseDate()?.get(Calendar.YEAR)?.toString() ?: ""

}