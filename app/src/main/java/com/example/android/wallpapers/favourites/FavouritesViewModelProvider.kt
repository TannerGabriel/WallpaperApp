package com.example.android.wallpapers.favourites

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class FavouritesViewModelProvider(private val favouritesRepository: FavouritesRepository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavouritesViewModel(favouritesRepository) as T
    }
}