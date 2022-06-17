package com.alex.kotlinmovies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alex.kotlinmovies.REALIZATION
import com.alex.kotlinmovies.data.Movies
import com.alex.kotlinmovies.model.repository.RetrofitRepository
import com.alex.kotlinmovies.model.room.MoviesRoomDatabase
import com.alex.kotlinmovies.model.room.repository.MoviesRepositoryRealization
import kotlinx.coroutines.launch
import retrofit2.Response

class PopularMoviesFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RetrofitRepository()
    val movies: MutableLiveData<Response<Movies>> = MutableLiveData()
    val context = application

    fun getPopularMoviesRetrofit() {
        viewModelScope.launch {
            movies.value = repository.getPopularMovies()
        }
    }

    fun initDatabase(){
        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
        REALIZATION = MoviesRepositoryRealization(daoMovie)
    }
}