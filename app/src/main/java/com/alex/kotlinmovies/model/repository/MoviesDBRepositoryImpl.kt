package com.alex.kotlinmovies.model.repository

import com.alex.kotlinmovies.APIKEY
import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.data.Movies
import com.alex.kotlinmovies.data.Trailer
import com.alex.kotlinmovies.model.apis.TMDBClient
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {

    private val apiInterface = TMDBClient.create()

    override fun getMovies(): Call<Movies> {                           // запрос в API
        return apiInterface.getMovies(APIKEY, "ru-RU", 1)
    }

    override fun getMovieDetails(id:Int, apikey: String): Call<MovieDetails>{
        return apiInterface.getMovieDetails(id, apikey, "ru-RU")
    }

    override fun getTrailer(id: Int, apikey: String): Call<Trailer> {
        return  apiInterface.getTrailer(id, apikey)
    }


}