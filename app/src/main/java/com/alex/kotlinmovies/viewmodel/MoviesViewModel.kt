package com.alex.kotlinmovies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alex.kotlinmovies.data.*
import com.alex.kotlinmovies.model.repository.MoviesDBRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(private val mMoviesRepository: MoviesDBRepository) {

    private val _movies = MutableLiveData<List<Result?>>()
    val movies: LiveData<List<Result?>> = _movies

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _trailers = MutableLiveData<Trailer>()
    val trailers: LiveData<Trailer> = _trailers

//    private val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                _movies.postValue(response.body()?.results)
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("test", "fail")
            }
        })
    }

    fun getMovieDetails(id: Int, apiKey: String){
        val response = mMoviesRepository.getMovieDetails(id, apiKey)
        response.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                _movieDetails.postValue(response.body())
            }
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.d("test", "failMovieDetails")
            }
        })
    }

    fun getTrailer(id: Int, apiKey: String){
        val response = mMoviesRepository.getTrailer(id, apiKey)
        response.enqueue(object: Callback<Trailer>{
            override fun onResponse(call: Call<Trailer>, response: Response<Trailer>) {
                _trailers.postValue(response.body())
                Log.d("test", "trailer $response")
            }
            override fun onFailure(call: Call<Trailer>, t: Throwable) {
                Log.d("test", "trailer fail")
            }
        })
    }
}