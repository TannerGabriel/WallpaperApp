package com.example.android.wallpapers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.wallpapers.data.Category
import com.example.android.wallpapers.item.CategorieItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_categories.*


/**
 * A simple [Fragment] subclass.
 *
 */
class CategoriesFragment : Fragment() {

    private lateinit var groupAdapter: GroupAdapter<ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Initialize the recycler adapter and set the spanCount
        groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 2
        }

        val category = Category("Landscape", "https://images.pexels.com/photos/559768/pexels-photo-559768.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260")
        groupAdapter.add(CategorieItem(category, context!!))
        groupAdapter.add(CategorieItem(category, context!!))
        groupAdapter.add(CategorieItem(category, context!!))
        groupAdapter.add(CategorieItem(category, context!!))
        groupAdapter.add(CategorieItem(category, context!!))

        return inflater.inflate(R.layout.fragment_categories, container, false)
    }


    override fun onStart() {
        super.onStart()

        //Ably the span count and the adapter to the recyclerview
        categories_recyclerview.apply {
            layoutManager = LinearLayoutManager(context!!)
            adapter = groupAdapter
        }
    }


}
