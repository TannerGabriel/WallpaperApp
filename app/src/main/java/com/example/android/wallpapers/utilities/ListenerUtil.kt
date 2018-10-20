package com.example.android.wallpapers.utilities

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.android.wallpapers.*
import com.example.android.wallpapers.FavouritesFragment
import com.example.android.wallpapers.wallpapers.WallpaperFragment

class  ListenerUtil{
    companion object {

        private var selectedFragment : Fragment? = null

        fun getBottomNavigationListener(supportFragmentManager: FragmentManager): BottomNavigationView.OnNavigationItemSelectedListener{
            val  navListener = BottomNavigationView.OnNavigationItemSelectedListener(){


                when(it.itemId){
                    R.id.wallpapers -> {
                        selectedFragment = WallpaperFragment()
                        true
                    }
                    R.id.categories ->{
                        selectedFragment = CategoriesFragment()
                        true
                    }
                    R.id.favourites ->{
                        selectedFragment = FavouritesFragment()
                        true
                    }
                    R.id.settings ->{
                        selectedFragment = SettingsFragment()
                        true
                    }

                    else ->{
                        false
                    }
                }

                supportFragmentManager.beginTransaction().replace(R.id.fragment_holder, selectedFragment!!).commit()
                true
            }

            return navListener
        }

        fun setFragment(supportFragmentManager: FragmentManager, fragment: Fragment){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_holder, fragment).commit()
        }

        fun getSelectedFragment(): Fragment{
            return selectedFragment!!
        }
    }
}