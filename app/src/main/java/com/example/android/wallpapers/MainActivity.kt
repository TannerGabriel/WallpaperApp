package com.example.android.wallpapers

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.wallpapers.categories.CategorieViewModel
import com.example.android.wallpapers.categories.CategoriesFragment
import com.example.android.wallpapers.utilities.InjectorUtils
import com.example.android.wallpapers.utilities.ListenerUtil
import com.example.android.wallpapers.wallpapers.WallpaperFragment
import com.example.android.wallpapers.wallpapers.WallpaperViewModel
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
