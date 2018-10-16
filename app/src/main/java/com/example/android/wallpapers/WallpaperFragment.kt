package com.example.android.wallpapers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.item.WallpaperItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_wallpaper.*


/**
 * A simple [Fragment] subclass.
 *
 */
class WallpaperFragment() : Fragment() {

    private lateinit var groupAdapter: GroupAdapter<ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //Initialize the recycler adapter and set the spanCount
        groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 2
        }



        val wallpaper = Wallpaper("veeterzy","https://images.pexels.com/photos/355423/pexels-photo-355423.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
        groupAdapter.add(WallpaperItem(wallpaper,getContext()!!))
        groupAdapter.add(WallpaperItem(wallpaper, activity?.baseContext!!))
        groupAdapter.add(WallpaperItem(wallpaper, activity?.applicationContext!!))
        groupAdapter.add(WallpaperItem(wallpaper, activity?.application!!))
        groupAdapter.add(WallpaperItem(wallpaper , context!!))
        val wallpaper2 = Wallpaper("veeterzy","https://images.pexels.com/photos/1115487/pexels-photo-1115487.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
        groupAdapter.add(WallpaperItem(wallpaper2 , context!!))
        groupAdapter.add(WallpaperItem(wallpaper2 , context!!))
        groupAdapter.add(WallpaperItem(wallpaper2 , context!!))
        groupAdapter.add(WallpaperItem(wallpaper2 , context!!))
        groupAdapter.add(WallpaperItem(wallpaper2 , context!!))


        return inflater.inflate(R.layout.fragment_wallpaper, container, false)

    }

    override fun onStart() {
        super.onStart()

        //Ably the span count and the adapter to the recyclerview
        wallpaper_recyclerview.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = groupAdapter
        }

        val wallpaper = Wallpaper("veeterzy","https://images.pexels.com/photos/1482778/pexels-photo-1482778.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")

        groupAdapter.add(WallpaperItem(wallpaper , context!!))
        groupAdapter.add(WallpaperItem(wallpaper , context!!))
    }
}
