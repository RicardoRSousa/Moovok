package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class MovieDetails(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    val budget: Int? = null,
    val genres: List<Genre>? = null,
    val id: Int? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val runtime: Int? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) {
    fun getImdbUrl(): String {
        return "https://www.imdb.com/title/{$imdbId}/"
    }

    fun getReleaseYear(): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val cal = Calendar.getInstance()
        cal.time = format.parse(releaseDate)
        return cal.get(Calendar.YEAR).toString()
    }
}