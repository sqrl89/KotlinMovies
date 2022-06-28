package com.alex.kotlinmovies.view.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alex.kotlinmovies.REALIZATION
import com.alex.kotlinmovies.model.MovieItemModel

class FavoriteMoviesFragmentViewModel : ViewModel() {

    fun getAllMovies(): LiveData<List<MovieItemModel>> {
        return REALIZATION.allMovies
    }
}

