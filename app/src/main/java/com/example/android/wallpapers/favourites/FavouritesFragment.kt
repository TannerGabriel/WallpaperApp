package com.example.android.wallpapers.favourites


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.wallpapers.R
import com.example.android.wallpapers.authentication.SignInActivity
import com.google.firebase.auth.FirebaseAuth


/**
 * A simple [Fragment] subclass.
 *
 */
class FavouritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mAuth = FirebaseAuth.getInstance()

        if(mAuth.currentUser == null){
            val signInIntent = Intent(context!!, SignInActivity::class.java)
            startActivity(signInIntent)
        }

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }


}
