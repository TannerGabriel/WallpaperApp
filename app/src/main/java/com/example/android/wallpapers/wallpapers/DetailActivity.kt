package com.example.android.wallpapers.wallpapers

import android.app.WallpaperManager
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.android.wallpapers.R
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.utilities.Constants
import com.example.android.wallpapers.utilities.InjectorUtils
import com.example.android.wallpapers.utilities.WallpaperSet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.*
import java.io.IOException




class DetailActivity : AppCompatActivity() {

    private lateinit var wallpaper: Wallpaper
    private var bitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Get the viewmodel factory
        val factory = InjectorUtils.provideWallpaperViewModelFactory()

        //Getting the viewmodel
        val viewModel = ViewModelProviders.of(this, factory).get(WallpaperViewModel::class.java)


        getIntentData()
        initUI()

        val options = listOf(WallpaperSet.Homescreen.toString(), WallpaperSet.Lockscreen.toString(), WallpaperSet.Both.toString())

        set_background_button.setOnClickListener {
            selector("Set Wallpaper", options, { dialogInterface, i ->
                viewModel.getImage(wallpaper, this, options[i])
            })
        }

    }


    private fun initUI() {
        try {
            Picasso.get().load(wallpaper.pictureURl).placeholder(R.drawable.ic_wallpaper).into(detail_picture_imageview)
            creator_textview.text = wallpaper.creator
        }catch (e: IOException){

        }
    }

    private fun getIntentData() {
        val intent = intent
        wallpaper = intent.getParcelableExtra(Constants.WALLPAPER)
    }



}
