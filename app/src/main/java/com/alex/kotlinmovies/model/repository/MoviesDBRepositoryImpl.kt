package com.alex.kotlinmovies.model.repository

import com.alex.kotlinmovies.APIKEY
import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.data.Movies
import com.alex.kotlinmovies.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {

    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {                           // запрос в API
        return apiInterface.getMovies(APIKEY, "en-US", 1)
    }

    override fun getMovieDetails(id:Int, apikey: String): Call<MovieDetails>{
        return apiInterface.getMovieDetails(id, apikey)
    }

}