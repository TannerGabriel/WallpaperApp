package com.example.android.wallpapers.categories

import android.arch.lifecycle.MutableLiveData
import com.example.android.wallpapers.data.Category
import com.example.android.wallpapers.data.Wallpaper
import com.google.firebase.firestore.FirebaseFirestore


class CategoriesRepository{
    companion object {
        @Volatile
        private var instance: CategoriesRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CategoriesRepository().also { instance = it }
            }
    }

    private var mFirestore: FirebaseFirestore? = null

    init {
        mFirestore = FirebaseFirestore.getInstance()
        getAllCategories()
    }

    private val categories = MutableLiveData<List<Category>>()

    private fun getAllCategories(){


        mFirestore?.collection("Categories")?.addSnapshotListener { documentSnapshot, error ->
            val categorie = ArrayList<Category>()
            val collection = ArrayList<MutableMap<String, Any>>()

            if(documentSnapshot != null){
                documentSnapshot.documents.forEach {
                    val collections = it?.data
                    if (collections != null) {
                        collection.add(collections)
                    }
                }
            }

            collection.forEach {
                val image = it.get("image")
                val title = it.get("title")
                println("Title: $title , Image: $image")
                categorie.add(Category(title.toString(), image.toString()))
            }

            categories.value = categorie
        }
    }

    fun getCategories() = categories

    private val wallpapers = MutableLiveData<List<Wallpaper>>()

    private fun getWallpapers(categoryTitle: String){
        val wallpaper = ArrayList<Wallpaper>()
        val mFirestore = FirebaseFirestore.getInstance()

        mFirestore.collection("Categories").document(categoryTitle).collection("images").addSnapshotListener {
                documentSnapshot, firebaseFirestoreException ->
            if(documentSnapshot != null){
                documentSnapshot.documents.forEach {
                    val image = it.get("image")
                    val creator = it.get("creator")
                    val website = it.get("website")
                    wallpaper.add(Wallpaper(creator.toString(), image.toString()))
                }
            }
            wallpapers.value = wallpaper
        }

    }

    fun loadWallpapers(categoryTitle: String){
        getWallpapers(categoryTitle)
    }

   fun getCategorieWallpapers() = wallpapers
}