package com.example.android.wallpapers.item

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.android.wallpapers.DetailActivity
import com.example.android.wallpapers.R
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.utilities.Constants
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.wallpaper_item.view.*
import java.security.AccessController.getContext

class WallpaperItem (wallpaper: Wallpaper, context: Context): Item(){

    val wallpaper = wallpaper
    val context = context

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(wallpaper.pictureURl).placeholder(R.drawable.ic_wallpaper).into(viewHolder.itemView.wallpaper_imageview)

        viewHolder.itemView.like_button.setOnClickListener {

        }

        viewHolder.itemView.item_layout.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(Constants.WALLPAPER, wallpaper)
            context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.wallpaper_item


}