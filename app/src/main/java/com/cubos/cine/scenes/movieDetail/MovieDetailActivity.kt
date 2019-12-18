package com.cubos.cine.scenes.movieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cubos.cine.R
import com.cubos.cine.models.Movie
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "movie"
        const val TRANSITION_IMAGE = "image"
    }

    private lateinit var viewModelFactory: MovieDetailViewModelFactory

    private val viewModel: MovieDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        intent?.getStringExtra(MOVIE)?.let {
            val json = it
            val movie  = Gson().fromJson(json, Movie::class.java)
            viewModelFactory = MovieDetailViewModelFactory(movie)
        }

        backButton.setOnClickListener {
            this.onBackPressed()
        }

        initObservables()

    }

    private fun initObservables() {
        viewModel.movieOverView.observe(this, Observer {
            movieOverView.text = it
        })

        viewModel.movieBackdropURL.observe(this, Observer {
            val picasso = Picasso.Builder(this)
                .listener { _, _, _ -> movieBackdropImageView.visibility = View.GONE }
                .build()

            picasso.load(it).into(movieBackdropImageView)
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
    }
}
