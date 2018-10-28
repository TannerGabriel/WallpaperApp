package com.example.android.wallpapers.wallpapers


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.wallpapers.R
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.item.WallpaperItem
import com.example.android.wallpapers.utilities.InjectorUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_wallpaper.*


/**
 * A simple [Fragment] subclass.
 *
 */
class WallpaperFragment : Fragment() {

    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private var data: List<Wallpaper>? = null

    private var viewModel: WallpaperViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //Initialize the recycler adapter and set the spanCount
        groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 2
        }


        initUI()


        return inflater.inflate(R.layout.fragment_wallpaper, container, false)

    }

    private fun initUI() {
        //Get the viewmodel factory
        val factory = InjectorUtils.provideWallpaperViewModelFactory()

        //Getting the viewmodel
        viewModel = ViewModelProviders.of(this, factory).get(WallpaperViewModel::class.java)


    }


    private fun addObserver(){
        viewModel?.getWallpapers()?.observe(this, Observer {wallpapers ->
            data = null
            data = wallpapers

            updateAdapter()
        })

    }

    override fun onStart() {
        super.onStart()

        //Ably the span count and the adapter to the recyclerview
        wallpaper_recyclerview.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = groupAdapter
        }

        addObserver()
    }

    private fun updateAdapter() {
        groupAdapter.clear()

        if(data != null) {
            data!!.forEach {
                groupAdapter.add(WallpaperItem(it, context!!))
            }
        }
    }
}
