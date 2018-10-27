package com.example.android.wallpapers.categories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class CategorieViewModelProvider(private val categoriesRepository: CategoriesRepository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategorieViewModel(categoriesRepository) as T
    }
}