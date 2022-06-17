package com.alex.kotlinmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alex.kotlinmovies.REALIZATION
import com.alex.kotlinmovies.data.MovieItemModel

class FavoriteMoviesFragmentViewModel : ViewModel() {

    fun getAllMovies(): LiveData<List<MovieItemModel>> {
        return REALIZATION.allMovies
    }
}