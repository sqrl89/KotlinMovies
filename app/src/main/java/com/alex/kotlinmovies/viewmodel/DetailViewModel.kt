package com.alex.kotlinmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.kotlinmovies.REALIZATION
import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.data.MovieItemModel
import com.alex.kotlinmovies.data.Trailer
import com.alex.kotlinmovies.data.room.repository.MoviesRepositoryRealization
import com.alex.kotlinmovies.model.repository.RetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val repository = RetrofitRepository()

    val movieDetails: MutableLiveData<Response<MovieDetails>> = MutableLiveData()
    val trailers: MutableLiveData<Response<Trailer>> = MutableLiveData()

    fun insert(movieItemModel: MovieItemModel, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.insertMovie(movieItemModel){
                onSuccess()
            }
        }

    fun delete(movieItemModel: MovieItemModel, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.deleteMovie(movieItemModel){
                onSuccess()
            }
        }

    fun getMovieDetails(id: Int, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetails.value = repository.getMovieDetails(id, apiKey)
        }
    }

    fun getTrailers(id: Int, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            trailers.value = repository.getTrailer(id, apiKey)
        }
    }


}