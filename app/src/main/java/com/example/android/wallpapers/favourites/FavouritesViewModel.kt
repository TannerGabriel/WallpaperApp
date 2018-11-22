package com.example.android.wallpapers.favourites

import android.arch.lifecycle.ViewModel
import com.example.android.wallpapers.wallpapers.WallpaperRepository

class FavouritesViewModel(private val favouritesRepository: FavouritesRepository): ViewModel(){

    fun getFavourites() = favouritesRepository.getFavourites()
}
