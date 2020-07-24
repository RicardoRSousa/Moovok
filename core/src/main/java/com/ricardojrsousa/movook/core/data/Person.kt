package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Person(
    @SerializedName("cast_id")
    val castId: Int,
    val character: String?,
    @SerializedName("credit_id")
    val creditId: String?,
    val gender: Int,
    override val id: String,
    val name: String,
    val order: Int?,
    @SerializedName("profile_path")
    val profilePath: String,
    val birthday: String?,
    val deathday: String?,
    val biography: String,
    val popularity: Double,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    val imdbId: String,
    var credits: Credit
) : Identifiable {

    fun getAge(): String {
        if (!birthday.isNullOrBlank()) {
            val format = SimpleDateFormat("yyyy-MM-dd")

            val dateOfBirth = Calendar.getInstance()
            dateOfBirth.time = format.parse(birthday)

            val today = Calendar.getInstance()

            var age: Int = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR)

            if (today.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR)) {
                age--
            }

            return age.toString()
        } else {
            return ""
        }
    }

    fun getCreditsByReleaseDateDescending(): List<Movie> {
        return credits.movies.sortedByDescending { it.getReleaseDate() }
    }
}