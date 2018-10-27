package com.example.android.wallpapers.categories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.android.wallpapers.R
import com.example.android.wallpapers.R.id.categories
import com.example.android.wallpapers.data.Category
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.item.CategorieItem
import com.example.android.wallpapers.item.WallpaperItem
import com.example.android.wallpapers.utilities.Constants
import com.example.android.wallpapers.utilities.InjectorUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoryActivity : AppCompatActivity() {

    private var category: Category? = null
    private var title: String? = null

    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private var data: List<Wallpaper>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 2
        }

        category = getIntentData()
        initUI()
    }

    override fun onStart() {
        super.onStart()

        //Ably the span count and the adapter to the recyclerview
        category_wallpaper_recyclerview.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = groupAdapter
        }
    }

    private fun initUI() {
        if(category != null){
            title = category?.title
            supportActionBar?.title = title
        }

        //Get the viewmodel factory
        val factory = InjectorUtils.provideCategoriesViewModelFactory()

        //Getting the viewmodel
        val viewModel = ViewModelProviders.of(this, factory).get(CategorieViewModel::class.java)

        viewModel.loadWallpapers(title!!)

        viewModel.getCategorieWallpapers().observe(this, Observer {wallpapers ->
            data = null
            data = wallpapers

            updateAdapter()
        })
    }

    private fun updateAdapter() {
        groupAdapter.clear()
        data!!.forEach {
            groupAdapter.add(WallpaperItem(it, this))
        }
    }


    private fun getIntentData(): Category {
        return intent.getParcelableExtra<Category>(Constants.CATEGORY)
    }



}
