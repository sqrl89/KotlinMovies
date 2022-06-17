package com.alex.kotlinmovies.model.apis

import com.alex.kotlinmovies.data.MovieDetails
import com.alex.kotlinmovies.data.Movies
import com.alex.kotlinmovies.data.Trailer
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<Movies>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<MovieDetails>

    @GET("3/movie/{movie_id}/videos")
    suspend fun getTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): Response<Trailer>


//interface TMDBInterface {
//    @GET("3/movie/popular")
//    fun getMovies(
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String,
//        @Query("page") page: Int
//    ): Call<Movies>
//
//    @GET("3/movie/{movie_id}")
//    fun getMovieDetails(
//        @Path("movie_id") movieId: Int,
//        @Query("api_key") apiKey: String,
//        @Query("language") language: String
//    ): Call<MovieDetails>
//
//    @GET("3/movie/{movie_id}/videos")
//    fun getTrailer(
//        @Path("movie_id") movieId: Int,
//        @Query("api_key") apiKey: String,
//    ): Call<Trailer>
//

}