package com.example.android.wallpapers.wallpapers

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.utilities.WallpaperSet
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException

class WallpaperRepository{

    companion object {
        @Volatile
        private var instance: WallpaperRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: WallpaperRepository().also { instance = it }
            }
    }


    fun getImage(wallpaper: Wallpaper, context: Context, options: String) {
        doAsync {
            val bitmap = Picasso.get().load(wallpaper.pictureURl).get()

            uiThread {
                setWallpaper(bitmap, context, options)
            }
        }
    }

    @SuppressLint("NewApi")
    private fun setWallpaper(bitmap: Bitmap, context: Context, options: String) {
        val wallpaperManager = WallpaperManager.getInstance(context.applicationContext)
        try {
            when(options){
                WallpaperSet.Homescreen.toString() -> {
                    wallpaperManager.run {
                        setBitmap(bitmap)
                    }
                }

                WallpaperSet.Lockscreen.toString() ->{
                    wallpaperManager.run {
                        setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                    }
                }

                WallpaperSet.Both.toString() ->{
                    wallpaperManager.run {
                        setBitmap(bitmap)
                        setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}