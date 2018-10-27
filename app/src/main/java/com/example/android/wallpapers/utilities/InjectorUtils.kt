package com.example.android.wallpapers.utilities


import com.example.android.wallpapers.categories.CategoriesRepository
import com.example.android.wallpapers.categories.CategorieViewModelProvider
import com.example.android.wallpapers.wallpapers.WallpaperRepository
import com.example.android.wallpapers.wallpapers.WallpaperViewModelProvider

object InjectorUtils {
    fun provideWallpaperViewModelFactory(): WallpaperViewModelProvider {
        val noteRepository = WallpaperRepository.getInstance()
        return WallpaperViewModelProvider(noteRepository)
    }

    fun provideCategoriesViewModelFactory(): CategorieViewModelProvider {
        val categoriesRepository = CategoriesRepository.getInstance()
        return CategorieViewModelProvider(categoriesRepository)
    }
}