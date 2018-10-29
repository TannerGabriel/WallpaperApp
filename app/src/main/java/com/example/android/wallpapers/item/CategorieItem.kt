package com.example.android.wallpapers.item


import android.content.Context
import android.content.Intent
import com.example.android.wallpapers.R
import com.example.android.wallpapers.categories.CategoryActivity
import com.example.android.wallpapers.data.Category
import com.example.android.wallpapers.utilities.Constants
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.categorie_item.view.*

class CategorieItem(val category: Category, val context: Context): Item(){

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(category.imageUrl).into(viewHolder.itemView.categories_image)
        viewHolder.itemView.categories_heading.text = category.title

        viewHolder.itemView.categories_layout.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra(Constants.CATEGORY, category)
            context.startActivity(intent)
        }
    }

    override fun getLayout(): Int = R.layout.categorie_item

}