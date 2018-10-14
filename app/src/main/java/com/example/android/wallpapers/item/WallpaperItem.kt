package com.example.android.wallpapers.item

import android.content.Context
import android.net.Uri
import com.bumptech.glide.Glide
import com.example.android.wallpapers.R
import com.example.android.wallpapers.data.Wallpaper
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.wallpaper_item.view.*
import java.security.AccessController.getContext

class WallpaperItem (wallpaper: Wallpaper, context: Context): Item(){

    val wallpaper = wallpaper
    val context = context

    override fun bind(viewHolder: ViewHolder, position: Int) {
        //Picasso.get().load("https://images.pexels.com/photos/355423/pexels-photo-355423.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260").placeholder(R.drawable.ic_wallpaper).into(viewHolder.itemView.wallpaper_imageview)

        Glide.with(context)
            .load(wallpaper.pictureURl)
            .into(viewHolder.itemView.wallpaper_imageview)

        viewHolder.itemView.like_button.setOnClickListener {

        }
    }

    override fun getLayout(): Int = R.layout.wallpaper_item


}