package com.cubos.cine.scenes.home.onTheaters
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.PagerSnapHelper

import com.cubos.cine.R
import com.cubos.cine.scenes.home.onTheaters.adapters.OnTheatersRecyclerAdapter
import com.cubos.cine.scenes.movieDetail.MovieDetailActivity
import com.cubos.cine.scenes.movieDetail.MovieDetailActivity.Companion.MOVIE
import com.cubos.cine.scenes.movieDetail.MovieDetailViewModel
import com.cubos.cine.views.layoutManager.CenterZoomLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_on_theaters.*

class OnTheatersFragment : Fragment() {
    private val viewModel: OnTheatersViewModel by lazy {
        ViewModelProviders.of(this)[OnTheatersViewModel::class.java]
    }

    private lateinit var adapter: OnTheatersRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_on_theaters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservables()
    }

    private fun initUi() {
        adapter = OnTheatersRecyclerAdapter { movie, view ->
            val options = ActivityOptions
                .makeSceneTransitionAnimation(activity, view, MovieDetailActivity.TRANSITION_IMAGE)

            val json = Gson().toJson(movie)

            startActivity(Intent(activity, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE, json)
            }, options.toBundle())
        }

        context?.let {
            onTheathersRecyclerView?.layoutManager = CenterZoomLayoutManager(it)
        }
        onTheathersRecyclerView?.adapter = adapter
        PagerSnapHelper().attachToRecyclerView(onTheathersRecyclerView)
    }

    private fun initObservables() {
        viewModel.isLoading.observe(this, Observer {
            if (it == true) {
                onTheatersProgressBar?.visibility = View.VISIBLE
            } else {
                onTheatersProgressBar?.visibility = View.GONE
            }
        })

        viewModel.onTheatersList.observe(this, Observer { list ->
            adapter.submitList(list)
        })
    }
}
