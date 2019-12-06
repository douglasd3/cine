package com.cubos.cine.scenes.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cubos.cine.R
import com.cubos.cine.scenes.home.adapters.HomePageAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewPager.adapter = HomePageAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(homeViewPager)
    }
}
