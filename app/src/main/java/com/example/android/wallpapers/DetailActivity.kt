package com.example.android.wallpapers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.utilities.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.io.IOException

class DetailActivity : AppCompatActivity() {

    private lateinit var wallpaper: Wallpaper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getIntentData()
        initUI()

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
