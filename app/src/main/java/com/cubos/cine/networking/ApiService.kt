package com.cubos.cine.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        const val posterBaseURL = "https://image.tmdb.org/t/p/"
        const val posterBaseSize = "w400"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService() = retrofit.create(ApiMoviesInterface::class.java)

}