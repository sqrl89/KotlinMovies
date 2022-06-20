package com.alex.kotlinmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.kotlinmovies.model.Movies
import com.alex.kotlinmovies.model.repository.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TopMoviesFragmentViewModel: ViewModel() {

    private val repository = RetrofitRepository()
    val topMovies: MutableLiveData<Response<Movies>> = MutableLiveData()

    fun getTopMoviesRetrofit() {
        viewModelScope.launch {
            topMovies.value = repository.getTopMovies()
        }
    }
}