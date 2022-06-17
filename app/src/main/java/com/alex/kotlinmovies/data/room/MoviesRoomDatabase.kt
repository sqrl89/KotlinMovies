package com.alex.kotlinmovies.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex.kotlinmovies.data.MovieItemModel
import com.alex.kotlinmovies.data.room.dao.MoviesDao

@Database(entities = [MovieItemModel::class], version = 2)
abstract class MoviesRoomDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

    companion object{
        private var database: MoviesRoomDatabase? = null

        fun getInstance(context: Context): MoviesRoomDatabase {
            return if (database == null){
                database = Room
                    .databaseBuilder(context, MoviesRoomDatabase::class.java, "db")
                    .build()
                database as MoviesRoomDatabase
            }else{
                database as MoviesRoomDatabase
            }
        }
    }
}