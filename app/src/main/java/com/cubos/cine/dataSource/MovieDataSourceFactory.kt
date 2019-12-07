package com.cubos.cine.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.cubos.cine.dataSource.MovieDataSource
import com.cubos.cine.models.Movie
import com.cubos.cine.models.MovieResponse

class MovieDataSourceFactory : DataSource.Factory<Int, Movie>() {

    private var dataSource: MovieDataSource? = null
    private var mutableLiveData: MutableLiveData<MovieDataSource>? = null

    override fun create(): DataSource<Int, Movie> {
        dataSource = MovieDataSource()
        mutableLiveData?.postValue(dataSource)
        return dataSource as MovieDataSource
    }
}