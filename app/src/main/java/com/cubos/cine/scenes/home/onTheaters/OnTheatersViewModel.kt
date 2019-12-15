package com.cubos.cine.scenes.home.onTheaters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cubos.cine.dataSource.MovieDataSourceFactory
import com.cubos.cine.dataSource.MovieDataSourceType
import com.cubos.cine.models.Movie

class OnTheatersViewModel: ViewModel() {
    val onTheatersList: LiveData<PagedList<Movie>> =
        LivePagedListBuilder<Int, Movie>(MovieDataSourceFactory(MovieDataSourceType.ONTHEATERS), 10)
            .setBoundaryCallback(object : PagedList.BoundaryCallback<Movie>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                }

                override fun onItemAtFrontLoaded(itemAtFront: Movie) {
                    super.onItemAtFrontLoaded(itemAtFront)
                }

                override fun onItemAtEndLoaded(itemAtEnd: Movie) {
                    super.onItemAtEndLoaded(itemAtEnd)
                }
            })
            .build()
}