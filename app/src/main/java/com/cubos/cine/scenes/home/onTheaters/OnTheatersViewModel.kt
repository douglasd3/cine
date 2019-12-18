package com.cubos.cine.scenes.home.onTheaters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cubos.cine.dataSource.MovieDataSourceFactory
import com.cubos.cine.dataSource.MovieDataSourceType
import com.cubos.cine.models.Movie

class OnTheatersViewModel: ViewModel() {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isLoading.value = true
    }

    val onTheatersList: LiveData<PagedList<Movie>> =
        LivePagedListBuilder<Int, Movie>(MovieDataSourceFactory(MovieDataSourceType.ONTHEATERS), 10)
            .setBoundaryCallback(object : PagedList.BoundaryCallback<Movie>() {
                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                    isLoading.value = false
                }

                override fun onItemAtFrontLoaded(itemAtFront: Movie) {
                    super.onItemAtFrontLoaded(itemAtFront)
                    isLoading.value = false
                }

                override fun onItemAtEndLoaded(itemAtEnd: Movie) {
                    super.onItemAtEndLoaded(itemAtEnd)
                    isLoading.value = false
                }
            })
            .build()

}