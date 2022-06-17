package com.alex.kotlinmovies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alex.kotlinmovies.REALIZATION
import com.alex.kotlinmovies.data.Movies
import com.alex.kotlinmovies.model.repository.RetrofitRepository
import com.alex.kotlinmovies.data.room.MoviesRoomDatabase
import com.alex.kotlinmovies.data.room.repository.MoviesRepositoryRealization
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


//    private val _movieDetails = MutableLiveData<MovieDetails>()
//    val movieDetails: LiveData<MovieDetails> = _movieDetails
//
//    private val _trailers = MutableLiveData<List<ResultX>>()
//    val trailers: LiveData<List<ResultX>> = _trailers
//
//
//    fun getMovies() {
//        val response = mMoviesRepository.getMovies()
//        response.enqueue(object : Callback<Movies> {
//            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
//                _movies.postValue(response.body()?.results)
//            }
//            override fun onFailure(call: Call<Movies>, t: Throwable) {
//                Log.d("test", "fail")
//            }
//        })
//    }
//
//    fun getMovieDetails(id: Int, apiKey: String){
//        val response = mMoviesRepository.getMovieDetails(id, apiKey)
//        response.enqueue(object : Callback<MovieDetails> {
//            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
//                _movieDetails.postValue(response.body())
//            }
//            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
//                Log.d("test", "failMovieDetails")
//            }
//        })
//    }
//
//    fun getTrailer(id: Int, apiKey: String){
//        val response = mMoviesRepository.getTrailer(id, apiKey)
//        response.enqueue(object: Callback<Trailer> {
//            override fun onResponse(call: Call<Trailer>, response: Response<Trailer>) {
//                _trailers.postValue(response.body()?.results)
//            }
//            override fun onFailure(call: Call<Trailer>, t: Throwable) {
//                Log.d("test", "trailer fail")
//            }
//        })
//    }
}