package com.alex.kotlinmovies.model.apis

import com.alex.kotlinmovies.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TMDBClient {

        fun create(): TMDBInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_BASE_URL)
                .build()
            return retrofit.create(TMDBInterface::class.java)
        }
    }
