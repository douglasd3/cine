package com.cubos.cine.scenes.movieDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cubos.cine.models.Movie
import com.cubos.cine.networking.ApiService

class MovieDetailViewModelFactory(val movie: Movie): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Movie::class.java).newInstance(movie)
    }
}

class MovieDetailViewModel(movie: Movie): ViewModel() {

    val movieOverView: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val movieBackdropURL: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        movieOverView.value = movie.overview
        movieBackdropURL.value = ApiService.posterBaseURL + ApiService.posterOriginalSize + movie.backdrop_path
    }

}