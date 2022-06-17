package com.alex.kotlinmovies.model.apis

import com.alex.kotlinmovies.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun create(): ApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_BASE_URL)
            .build()
        return retrofit.create(ApiService::class.java)
    }

}
