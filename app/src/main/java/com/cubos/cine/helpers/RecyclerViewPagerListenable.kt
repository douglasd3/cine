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
        assertRecyclerViewSetup(recyclerView)
        setUpSnapHelper(recyclerView, listener)
//        setUpScrollListener(recyclerView, listener)
    }

//    protected fun setUpScrollListener(recyclerView: RecyclerView, listener: RecyclerViewPagerStateListener) =
//        PagerSnapHelper(recyclerView, listener, maxPages)

    protected fun setUpSnapHelper(recyclerView: RecyclerView, listener: RecyclerViewPagerStateListener) =
        PagerSnapHelper().attachToRecyclerView(recyclerView)
//        PagerSnapHelperVerbose(recyclerView, listener).attachToRecyclerView(recyclerView)

    protected fun assertRecyclerViewSetup(recyclerView: RecyclerView) {
        if (recyclerView.layoutManager !is LinearLayoutManager) {
            throw IllegalArgumentException("RVPagerSnapHelperListenable can only work with a linear layout manager")
        }

        if ((recyclerView.layoutManager as LinearLayoutManager).orientation != LinearLayoutManager.HORIZONTAL) {
            throw IllegalArgumentException("RVPagerSnapHelperListenable can only work with a horizontal orientation")
        }
    }
}