package com.cubos.cine.scenes.home.adapters

import android.content.Context
import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cubos.cine.R
import com.cubos.cine.scenes.home.moviesList.MoviesListFragment
import com.cubos.cine.scenes.home.onTheaters.OnTheatersFragment

interface CustomPageAdapter {
    val titleList: ArrayList<String>
}


class HomePageAdapter(context: Context, fragmentManager: FragmentManager): CustomPageAdapter, FragmentPagerAdapter(fragmentManager) {
    private val fragmentList: ArrayList<Fragment> = arrayListOf(OnTheatersFragment(), MoviesListFragment())
    override val titleList: ArrayList<String> = arrayListOf(context.resources.getString(R.string.onTheatersTitle), context.resources.getString(R.string.moviesListTitle))

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int  = fragmentList.size

    //override fun getPageTitle(position: Int): CharSequence? = titleList[position]
}