package com.ricardojrsousa.movook.framework.db

import androidx.room.*

/**
 * Created by ricardosousa on 25/05/2020
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieEntity(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieEntity>

    @Delete
    suspend fun deleteMovieEntity(movieEntity: MovieEntity)

}