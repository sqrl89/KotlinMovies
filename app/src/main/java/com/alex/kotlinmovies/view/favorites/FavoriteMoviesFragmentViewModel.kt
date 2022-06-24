package com.alex.kotlinmovies.view.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.alex.kotlinmovies.REALIZATION
import com.alex.kotlinmovies.model.MovieItemModel
import com.alex.kotlinmovies.model.room.MoviesRoomDatabase
import com.alex.kotlinmovies.model.room.repository.MoviesRepositoryRealization

class FavoriteMoviesFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(){
        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
        REALIZATION = MoviesRepositoryRealization(daoMovie)
    }

    fun getAllMovies(): LiveData<List<MovieItemModel>> {
        return REALIZATION.allMovies
    }
}

