package com.alex.kotlinmovies.model.repository

import com.alex.kotlinmovies.model.MovieDetails
import com.alex.kotlinmovies.model.Movies
import com.alex.kotlinmovies.model.Trailer
import retrofit2.Call

interface MoviesDBRepository {

    fun getMovies(): Call<Movies>

    fun getMovieDetails(id:Int, apikey: String): Call<MovieDetails>

    fun getTrailer(id:Int, apikey: String): Call<Trailer>

}