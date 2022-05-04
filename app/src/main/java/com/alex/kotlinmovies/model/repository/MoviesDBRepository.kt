package com.alex.kotlinmovies.model.repository

import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.data.Movies
import retrofit2.Call

interface MoviesDBRepository {
    fun getMovies(): Call<Movies>

    fun getMovieDetails(id:Int, apikey: String): Call<MovieDetails>
}