package com.cubos.cine.helpers

import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

data class VisiblePageState(
    var index: Int,
    var view: View,
    @Px var viewCenterX: Int,
    @Px var distanceToSettledPixels: Int,
    var distanceToSettled: Float)

interface RecyclerViewPagerStateListener {
    fun onPageScroll(pagesState: List<VisiblePageState>) {}
    fun onScrollStateChanged(state: Int) {}
    fun onPageSelected(index: Int) {}
}

open class RecyclerViewPagerListenable(private val maxPages: Int = 3) : RecyclerView.OnScrollListener() {
    fun attachToRecyclerView(recyclerView: RecyclerView, listener: RecyclerViewPagerStateListener) {
        PagerSnapHelper().attachToRecyclerView(recyclerView)

    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

}