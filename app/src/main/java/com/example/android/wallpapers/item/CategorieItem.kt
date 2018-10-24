package com.example.android.wallpapers.item


import android.content.Context
import android.widget.Toast
import com.example.android.wallpapers.R
import com.example.android.wallpapers.data.Category
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.categorie_item.view.*

class CategorieItem(val category: Category, val context: Context): Item(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(category.imageUrl).into(viewHolder.itemView.categories_image)
        viewHolder.itemView.categories_heading.text = category.title

        viewHolder.itemView.categories_layout.setOnClickListener {
            Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getLayout(): Int = R.layout.categorie_item

}