package com.alex.kotlinmovies.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alex.kotlinmovies.model.MovieItemModel


@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieItemModel: MovieItemModel)

    @Delete
    suspend fun delete(movieItemModel: MovieItemModel)

    @Query("SELECT * from movie_table")
    fun getAllMovies(): LiveData<List<MovieItemModel>>
}
