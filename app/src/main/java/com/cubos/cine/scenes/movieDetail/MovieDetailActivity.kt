package com.cubos.cine.scenes.movieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cubos.cine.R

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val VIEW_MODEL = "viewModel"
        const val TRANSITION_IMAGE = "image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
    }
}
