package com.alex.kotlinmovies.model.repository

import com.alex.kotlinmovies.APIKEY
import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.data.Movies
import com.alex.kotlinmovies.data.Trailer
import com.alex.kotlinmovies.model.apis.RetrofitInstance
import retrofit2.Response

class RetrofitRepository  {

    private val apiInterface = RetrofitInstance.create()

    suspend fun getPopularMovies(): Response<Movies> {                           // запрос в API
        return apiInterface.getPopularMovies(APIKEY, "ru-RU", 1)
    }

    suspend fun getTopMovies(): Response<Movies> {
        return apiInterface.getTopMovies(APIKEY, "ru-RU", 1)
    }

    suspend fun getMovieDetails(id:Int, apikey: String): Response<MovieDetails>{
        return apiInterface.getMovieDetails(id, APIKEY, "ru-RU")
    }

    suspend fun getTrailer(id: Int, apikey: String): Response<Trailer> {
        return  apiInterface.getTrailer(id, APIKEY)
    }


}