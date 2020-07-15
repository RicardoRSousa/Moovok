package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("cast_id")
    val castId: Int,
    val character: String?,
    @SerializedName("credit_id")
    val creditId: String?,
    val gender: Int,
    val id: Int,
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
)