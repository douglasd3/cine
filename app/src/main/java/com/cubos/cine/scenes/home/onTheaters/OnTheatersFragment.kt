package com.cubos.cine.scenes.home.onTheaters

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.cubos.cine.R
import com.cubos.cine.helpers.RecyclerViewPagerListenable
import com.cubos.cine.helpers.RecyclerViewPagerStateListener
import com.cubos.cine.helpers.VisiblePageState
import com.cubos.cine.scenes.home.moviesList.MoviesListViewModel
import com.cubos.cine.scenes.home.moviesList.adapters.MovieRecyclerAdapter
import com.cubos.cine.scenes.home.onTheaters.adapters.OnTheatersRecyclerAdapter
import com.cubos.cine.views.decorators.RecyclerViewPagerDecorator
import kotlinx.android.synthetic.main.fragment_movies_list.*
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
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_on_theaters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservables()
    }

    private fun initUi() {
        adapter = OnTheatersRecyclerAdapter { movie, view -> }

        // Usual setups
        onTheathersRecyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        onTheathersRecyclerView?.adapter = adapter
        // Decorator set-up
        RecyclerViewPagerListenable().attachToRecyclerView(
            onTheathersRecyclerView,
            object : RecyclerViewPagerStateListener {
                override fun onPageScroll(pagesState: List<VisiblePageState>) {/*...*/}
                override fun onPageSelected(index: Int) {/*...*/}
                override fun onScrollStateChanged(state: Int) {}

            })

//        context?.let {
//            val cardWidthPixels = it.resources.displayMetrics.widthPixels * 0.60f
//            val cardHintPercent = 0.01f
//            onTheathersRecyclerView?.addItemDecoration(RecyclerViewPagerDecorator(it, cardWidthPixels.toInt(), cardHintPercent))
//        }
    }

    private fun initObservables() {
        viewModel.onTheatersList.observe(this, Observer { list ->
            adapter.submitList(list)
        })
    }
}
