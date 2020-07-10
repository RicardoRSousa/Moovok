package com.ricardojrsousa.movook.framework.db

import androidx.room.TypeConverter


/**
 * Created by ricardosousa on 27/05/2020
 */
class MoviesTypeConverters {
    private val delimiter = ","

    @TypeConverter
    fun toGenreList(genres: String?): List<Int> {
        return genres?.split(delimiter)!!.map { it.toInt() }
    }

    @TypeConverter
    fun fromGenreList(genres: List<Int?>?): String {
        return genres?.joinToString(delimiter) ?: ""
    }
}