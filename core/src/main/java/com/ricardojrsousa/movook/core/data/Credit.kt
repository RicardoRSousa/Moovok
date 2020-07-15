package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("cast")
    var movies: List<Movie>
)