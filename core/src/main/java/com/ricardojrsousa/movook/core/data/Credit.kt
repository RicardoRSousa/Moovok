package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("cast")
    var movies: List<Movie>
) {

    fun getPopularMovies(howMany: Int, minimunVotes: Int) = movies.sortedByDescending { it.voteAverage }.filter { it.voteCount > minimunVotes }.take(howMany)
}