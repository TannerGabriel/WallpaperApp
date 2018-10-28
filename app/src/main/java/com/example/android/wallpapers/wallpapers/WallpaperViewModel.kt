package com.example.android.wallpapers.wallpapers

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.android.wallpapers.data.Wallpaper

class WallpaperViewModel(private val wallpaperRepository: WallpaperRepository): ViewModel(){

    fun getImage(wallpaper: Wallpaper, context: Context, options: String) = wallpaperRepository.getImage(wallpaper, context, options)

    fun getWallpapers() = wallpaperRepository.getWallpapers()

}