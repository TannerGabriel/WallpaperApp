package com.example.android.wallpapers.categories

import android.arch.lifecycle.ViewModel
import com.example.android.wallpapers.categories.CategoriesRepository

class CategorieViewModel(val categoriesRepository: CategoriesRepository): ViewModel(){

    fun getCategories() = categoriesRepository.getCategories()

    fun getCategorieWallpapers() = categoriesRepository.getCategorieWallpapers()

    fun loadWallpapers(categoryTitle: String) = categoriesRepository.loadWallpapers(categoryTitle)
}