package com.example.android.wallpapers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.example.android.wallpapers.utilities.ListenerUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
        supportActionBar?.title = "Wallpapers"

        ListenerUtil.setFragment(supportFragmentManager, WallpaperFragment())

        val navListener = ListenerUtil.getBottomNavigationListener(supportFragmentManager)
        bottom_navigation.setOnNavigationItemSelectedListener(navListener)
    }
}
