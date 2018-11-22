package com.example.android.wallpapers.favourites


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.wallpapers.R
import com.example.android.wallpapers.R.id.wallpapers
import com.example.android.wallpapers.authentication.SignInActivity
import com.example.android.wallpapers.data.Wallpaper
import com.example.android.wallpapers.item.WallpaperItem
import com.example.android.wallpapers.utilities.InjectorUtils
import com.example.android.wallpapers.wallpapers.WallpaperViewModel
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.fragment_wallpaper.*


/**
 * A simple [Fragment] subclass.
 *
 */
class FavouritesFragment : Fragment() {

    private lateinit var groupAdapter: GroupAdapter<ViewHolder>

    private var viewModel: FavouritesViewModel? = null

    private var data: List<Wallpaper>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mAuth = FirebaseAuth.getInstance()

        if(mAuth.currentUser == null){
            val signInIntent = Intent(context!!, SignInActivity::class.java)
            startActivity(signInIntent)
        }

        //Initialize the recycler adapter and set the spanCount
        groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 2
        }

        initUI()


        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    private fun initUI() {
        //Get the viewmodel factory
        val factory = InjectorUtils.provideFavouritesViewModelFactory()

        //Getting the viewmodel
        viewModel = ViewModelProviders.of(this, factory).get(FavouritesViewModel::class.java)


    }


    private fun addObserver(){
        viewModel?.getFavourites()?.observe(this, Observer {fav ->
            data = null
            data = fav

            updateAdapter()
        })

    }

    override fun onStart() {
        super.onStart()

        //Ably the span count and the adapter to the recyclerview
        favourites_recyclerview.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = groupAdapter
        }

        addObserver()
    }

    private fun updateAdapter() {
        groupAdapter.clear()

        if(data != null) {
            data!!.forEach {
                groupAdapter.add(WallpaperItem(it, context!!))
            }
        }
    }


}
