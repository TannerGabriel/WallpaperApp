package com.example.android.wallpapers.favourites

import android.arch.lifecycle.MutableLiveData
import com.example.android.wallpapers.R.id.categories
import com.example.android.wallpapers.R.id.wallpapers
import com.example.android.wallpapers.data.Category
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.wallpapers.WallpaperRepository
import com.google.android.gms.internal.measurement.zzsl.init
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class  FavouritesRepository{

    companion object {
        @Volatile
        private var instance: FavouritesRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FavouritesRepository().also { instance = it }
            }
    }


    private var mFirestore: FirebaseFirestore? = null
    private var mAuth: FirebaseAuth? = null

    init {
        mFirestore = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()

        getAllFavourites()
    }

    private val favourites = MutableLiveData<List<Wallpaper>>()

    private fun getAllFavourites(){
        val uid = mAuth?.uid

        mFirestore?.collection("users")?.document(uid!!)?.collection("favourites")?.addSnapshotListener { documentSnapshot, error ->
            val favouritesList = ArrayList<Wallpaper>()

            if(documentSnapshot != null){
                documentSnapshot.documents.forEach {
                    if(it.data != null){
                        val creator: String = it!!.data?.get("creator").toString()
                        val image: String = it!!.data?.get("pictureURl").toString()
                        val wallpaper: Wallpaper = Wallpaper(creator, image)
                        favouritesList.add(wallpaper)
                    }
                }
            }



            favourites.value = favouritesList
        }
    }

    fun getFavourites() = favourites
}

