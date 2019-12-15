package com.cubos.cine.scenes.home.onTheaters
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
import com.cubos.cine.views.layoutManager.CenterZoomLayoutManager
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
        adapter = OnTheatersRecyclerAdapter { movie, view -> }

        context?.let {
            onTheathersRecyclerView?.layoutManager = CenterZoomLayoutManager(it)
        }
        onTheathersRecyclerView?.adapter = adapter
        PagerSnapHelper().attachToRecyclerView(onTheathersRecyclerView)
    }

    private fun initObservables() {
        viewModel.onTheatersList.observe(this, Observer { list ->
            adapter.submitList(list)
        })
    }
}
