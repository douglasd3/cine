package com.cubos.cine.networking

import com.cubos.cine.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMoviesInterface {

    companion object {
        private const val API_KEY_QUERY = "api_key"
        private const val API_QUERY_VALUE = "b727fd6dc7a1a30f2ce43cc0fd56c15f"
    }

    @GET("/3/movie/popular")
    fun handleGetPopularMovies(@Query("page") page: Int = 1,
                      @Query(API_KEY_QUERY) api_key: String = API_QUERY_VALUE) : Call<MovieResponse?>

}