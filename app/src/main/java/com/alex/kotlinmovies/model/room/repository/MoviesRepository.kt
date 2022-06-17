package com.alex.kotlinmovies.model.room.repository

import androidx.lifecycle.LiveData
import com.alex.kotlinmovies.data.MovieItemModel

interface MoviesRepository {
    val allMovies: LiveData<List<MovieItemModel>>
    suspend fun insertMovie(movieItemModel: MovieItemModel, onSuccess:() -> Unit)
    suspend fun deleteMovie(movieItemModel: MovieItemModel, onSuccess:() -> Unit)

}