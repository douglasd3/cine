package com.cubos.cine.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.cubos.cine.models.Movie

class MovieDataSourceFactory(private val type: MovieDataSourceType) : DataSource.Factory<Int, Movie>() {

    private var dataSource: MovieDataSource? = null
    private var mutableLiveData: MutableLiveData<MovieDataSource>? = null

    override fun create(): DataSource<Int, Movie> {
        dataSource = MovieDataSource(type)
        mutableLiveData?.postValue(dataSource)
        return dataSource as MovieDataSource
    }
}