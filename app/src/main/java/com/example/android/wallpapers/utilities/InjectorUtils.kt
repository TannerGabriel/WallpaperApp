package com.example.android.wallpapers.utilities


import com.example.android.wallpapers.categories.CategoriesRepository
import com.example.android.wallpapers.categories.CategorieViewModelProvider
import com.example.android.wallpapers.favourites.FavouritesRepository
import com.example.android.wallpapers.favourites.FavouritesViewModel
import com.example.android.wallpapers.favourites.FavouritesViewModelProvider
import com.example.android.wallpapers.wallpapers.WallpaperRepository
import com.example.android.wallpapers.wallpapers.WallpaperViewModelProvider

object InjectorUtils {
    fun provideWallpaperViewModelFactory(): WallpaperViewModelProvider {
        val wallpaperRepository = WallpaperRepository.getInstance()
        return WallpaperViewModelProvider(wallpaperRepository)
    }

    fun provideCategoriesViewModelFactory(): CategorieViewModelProvider {
        val categoriesRepository = CategoriesRepository.getInstance()
        return CategorieViewModelProvider(categoriesRepository)
    }

    fun provideFavouritesViewModelFactory(): FavouritesViewModelProvider {
        val favouritesRepository = FavouritesRepository.getInstance()
        return FavouritesViewModelProvider(favouritesRepository)
    }
}
