package com.cubos.cine.scenes.home.moviesList

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import com.cubos.cine.scenes.home.moviesList.adapters.MovieRecyclerAdapter
import com.cubos.cine.scenes.movieDetail.MovieDetailActivity
import com.cubos.cine.scenes.movieDetail.MovieDetailActivity.Companion.MOVIE
import com.cubos.cine.scenes.movieDetail.MovieDetailViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesListFragment : Fragment() {
    private val viewModel: MoviesListViewModel by lazy {
        ViewModelProviders.of(this)[MoviesListViewModel::class.java]
    }

    private lateinit var adapter: MovieRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.cubos.cine.R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservables()
    }


    private fun initUi() {
        adapter = MovieRecyclerAdapter { movie, view ->
            val options = ActivityOptions
                .makeSceneTransitionAnimation(activity, view, MovieDetailActivity.TRANSITION_IMAGE)

            val json = Gson().toJson(movie)

            startActivity(Intent(activity, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE, json)
            }, options.toBundle())
        }

        recyclerView?.layoutManager = GridLayoutManager(this.context, 3)
        recyclerView?.adapter = adapter
    }

    private fun initObservables() {
        viewModel.isLoading.observe(this, Observer {
            if (it == true) {
                moviesProgressBar?.visibility = View.VISIBLE
            } else {
                moviesProgressBar?.visibility = View.GONE
            }
        })

        viewModel.moviesList.observe(this, Observer { list ->
            adapter.submitList(list)
        })
    }
}
