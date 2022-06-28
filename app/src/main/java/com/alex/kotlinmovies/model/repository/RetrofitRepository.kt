package com.alex.kotlinmovies.model.repository

import com.alex.kotlinmovies.APIKEY
import com.alex.kotlinmovies.model.MovieDetails
import com.alex.kotlinmovies.model.Movies
import com.alex.kotlinmovies.model.Trailer
import com.alex.kotlinmovies.model.apis.RetrofitInstance
import retrofit2.Response

class RetrofitRepository  {

    private val apiInterface = RetrofitInstance.create()

    suspend fun getMovieDetails(id:Int, apikey: String): Response<MovieDetails>{
        return apiInterface.getMovieDetails(id, APIKEY, "ru-RU")
    }

    suspend fun getTrailer(id: Int, apikey: String): Response<Trailer> {
        return  apiInterface.getTrailer(id, APIKEY)
    }


}