package com.cubos.cine.dataSource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.cubos.cine.models.Movie
import com.cubos.cine.models.MovieResponse
import com.cubos.cine.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class MovieDataSourceType {
    POPULAR,
    ONTHEATERS
}

class MovieDataSource(private val type: MovieDataSourceType) : PageKeyedDataSource<Int, Movie>() {

    private var newPage = 0
    private var apiCall: ((Int) -> Call<MovieResponse?>) = {
        when(type) {
            MovieDataSourceType.POPULAR -> ApiService().getService().handleGetPopularMovies(it)
            MovieDataSourceType.ONTHEATERS -> ApiService().getService().handleOnTheatersMovies(it)
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        newPage++
        val call = apiCall(newPage)
        call.enqueue(object : Callback<MovieResponse?> {
            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                Log.d("error", t.toString())
            }

            override fun onResponse(
                call: Call<MovieResponse?>,
                response: Response<MovieResponse?>
            ) {
                Log.d("error", response.toString())
                response.body()?.let {
                    Log.d("error", it.toString())
                    callback.onResult(it.results, null, newPage)
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        newPage++
        val call = ApiService().getService().handleGetPopularMovies(newPage)
        call.enqueue(object : Callback<MovieResponse?> {
            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {}

            override fun onResponse(
                call: Call<MovieResponse?>,
                response: Response<MovieResponse?>
            ) {
                Log.d("error", response.toString())
                response.body()?.let {
                    callback.onResult(it.results, newPage)
                }
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}

}