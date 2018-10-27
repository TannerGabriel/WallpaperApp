package com.example.android.wallpapers.categories


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.wallpapers.R
import com.example.android.wallpapers.data.Category
import com.example.android.wallpapers.item.CategorieItem
import com.example.android.wallpapers.utilities.InjectorUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_categories.*


/**
 * A simple [Fragment] subclass.
 *
 */
class CategoriesFragment : Fragment() {

    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private var data: List<Category>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Initialize the recycler adapter and set the spanCount
        groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 2
        }

        initUI()

        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    private fun updateAdapter() {
        groupAdapter.clear()
        data!!.forEach {
            groupAdapter.add(CategorieItem(it, context!!))
        }
    }


    override fun onStart() {
        super.onStart()

        //Ably the span count and the adapter to the recyclerview
        categories_recyclerview.apply {
            layoutManager = LinearLayoutManager(context!!)
            adapter = groupAdapter
        }
    }

    private fun initUI() {
        //Get the viewmodel factory
        val factory = InjectorUtils.provideCategoriesViewModelFactory()

        //Getting the viewmodel
        val viewModel = ViewModelProviders.of(this, factory).get(CategorieViewModel::class.java)

        viewModel.getCategories().observe(this, Observer {categories ->
            data = null
            data = categories

            updateAdapter()
        })
    }


}
