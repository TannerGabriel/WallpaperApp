package com.example.android.wallpapers.item

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.example.android.wallpapers.wallpapers.DetailActivity
import com.example.android.wallpapers.R
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.utilities.Constants
import com.example.android.wallpapers.utilities.DataUtil
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.wallpaper_item.view.*
import org.jetbrains.anko.design.snackbar

class WallpaperItem (wallpaper: Wallpaper, context: Context): Item(){

    val wallpaper = wallpaper
    val context = context

    @SuppressLint("ResourceType")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(wallpaper.pictureURl).placeholder(R.drawable.ic_wallpaper).into(viewHolder.itemView.wallpaper_imageview)

        viewHolder.itemView.like_button.setOnClickListener {
            snackbar(viewHolder.itemView.item_layout, "Liked")
        }

        viewHolder.itemView.item_layout.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(Constants.WALLPAPER, wallpaper)
            context.startActivity(intent)
        }

        viewHolder.itemView.like_button.setOnClickListener {
            DataUtil.setFavourite(wallpaper)
        }
    }

    override fun getLayout() = R.layout.wallpaper_item


}