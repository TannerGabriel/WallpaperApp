package com.example.android.wallpapers.utilities

import com.example.android.wallpapers.data.Wallpaper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DataUtil{
    companion object {
        private var mAuth : FirebaseAuth? = null
        private var mFirestore: FirebaseFirestore? = null

        fun setFavourite(wallpaper: Wallpaper){
            mAuth = FirebaseAuth.getInstance()
            mFirestore = FirebaseFirestore.getInstance()

            val url = wallpaper.pictureURl.replace("/", "\\")
            print("Setting")

            //mFirestore?.collection("users")?.document(mAuth?.uid!!)?.collection("favourites")?.document(url)?.set(wallpaper)
            mFirestore?.collection("users")?.document(mAuth?.uid!!)?.collection("favourites")?.document(url)?.get()?.addOnCompleteListener{
                   if(it.result!!.exists()){
                       mFirestore?.collection("users")?.document(mAuth?.uid!!)?.collection("favourites")?.document(url)?.delete()
                       print("Deleting")
                   }else{
                       mFirestore?.collection("users")?.document(mAuth?.uid!!)?.collection("favourites")?.document(url)?.set(wallpaper)
                       print("Favourite")
                   }
            }



        }
    }
}