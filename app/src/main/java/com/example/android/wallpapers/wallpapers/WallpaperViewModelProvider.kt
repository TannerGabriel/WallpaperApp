package com.example.android.wallpapers.wallpapers

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.android.wallpapers.data.Wallpaper

class WallpaperViewModelProvider(private val wallpaperRepository: WallpaperRepository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WallpaperViewModel(wallpaperRepository) as T
    }
}