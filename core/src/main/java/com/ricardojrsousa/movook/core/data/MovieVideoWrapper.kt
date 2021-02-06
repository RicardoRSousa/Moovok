package com.ricardojrsousa.movook.core.data

/**
 * Created by Ricardo Sousa on 06/02/2021.
 */
data class MovieVideoWrapper(
    val id: Int,
    val results: List<MovieVideo>
) {

    fun getYoutubeTrailerKey(): String? {
        return results.find { movie ->
            movie.type == MovieVideo.VideoTypes.TRAILER &&
                    movie.site == MovieVideo.VideoSite.YOUTUBE
        }?.key
    }
}