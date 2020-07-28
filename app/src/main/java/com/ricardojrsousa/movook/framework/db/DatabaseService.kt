package com.ricardojrsousa.movook.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by ricardosousa on 25/05/2020
 */
@Database(entities = [MovieEntity::class], version = 4)
abstract class DatabaseService : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "movies.db"

        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun movieDao(): MovieDao

}