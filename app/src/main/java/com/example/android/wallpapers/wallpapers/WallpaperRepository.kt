package com.example.android.wallpapers.wallpapers

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import com.example.android.wallpapers.R.id.categories
import com.example.android.wallpapers.data.Category
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.utilities.WallpaperSet
import com.google.firebase.firestore.FirebaseFirestore
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
            getAllWallpapers()
        }
    }

    private val wallpapers = MutableLiveData<List<Wallpaper>>()

    private fun getAllWallpapers(){
        val wallpaper = ArrayList<Wallpaper>()

        categories.value?.forEach {
            mFirestore?.collection("Categories")?.document(it.title)?.collection("images")?.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                if(documentSnapshot != null){
                    documentSnapshot.documents.forEach {
                        val creator = it.getString("creator")
                        val image = it.getString("image")
                        println("Image: $image")
                        wallpaper.add(Wallpaper(creator!!, image!!))
                    }
                }

            }
        }

        Handler().postDelayed({
            wallpapers.value = wallpaper
        }, 1000)
    }

    fun getWallpapers() = wallpapers

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