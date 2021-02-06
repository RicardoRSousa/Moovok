package com.ricardojrsousa.movook.core.data

import com.google.gson.annotations.SerializedName

data class MovieVideo(
    val id: String,
    val key: String,
    val name: String,
    val site: VideoSite,
    val size: String,
    val type: VideoTypes
) {

    enum class VideoTypes {
        @SerializedName("Trailer")
        TRAILER,

        @SerializedName("Teaser")
        TEASER,

        @SerializedName("Clip")
        CLIP,

        @SerializedName("Featurette")
        FEATURETTE,

        @SerializedName("Behind the Scenes")
        BEHIND_THE_SCENES,

        @SerializedName("Bloopers")
        BLOOPERS
    }

    enum class VideoSite{
        @SerializedName("YouTube")
        YOUTUBE,
        @SerializedName("Vimeo")
        VIMEO
    }

}
