package com.alex.kotlinmovies.view.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alex.kotlinmovies.REALIZATION
import com.alex.kotlinmovies.model.room.MoviesRoomDatabase
import com.alex.kotlinmovies.model.room.repository.MoviesRepositoryRealization

class MoviesActivityViewModel(application: Application) : AndroidViewModel(application)  {

    val context = application

    fun initDatabase(){
        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
        REALIZATION = MoviesRepositoryRealization(daoMovie)
    }
}