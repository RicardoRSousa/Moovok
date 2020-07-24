package com.ricardojrsousa.movook.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ricardojrsousa.movook.core.data.Movie

/**
 * Created by ricardosousa on 25/05/2020
 */

@Entity(tableName = "movies")
@TypeConverters(MoviesTypeConverters::class)
data class MovieEntity(
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    val overview: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>?,
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    val title: String,
    val popularity: Double,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int
) {
    companion object {
        fun fromMovie(movie: Movie) = MovieEntity(
            movie.posterPath, movie.overview, movie.releaseDate, movie.genreIds, movie.id,
            movie.originalTitle, movie.title, movie.popularity, movie.voteAverage, movie.voteCount
        )
    }

    fun toMovie() = Movie(
        posterPath,
        overview,
        releaseDate,
        genreIds,
        id,
        originalTitle,
        title,
        popularity,
        voteAverage,
        voteCount
    )
}