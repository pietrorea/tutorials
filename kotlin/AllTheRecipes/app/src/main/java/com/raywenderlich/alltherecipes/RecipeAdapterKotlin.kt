package com.raywenderlich.alltherecipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * Created by pietrorea on 10/22/17.
 */

class RecipeAdapterKotlin(context: Context, dataSource: ArrayList<Recipe>): BaseAdapter() {

    private var context = context
    private var dataSource = dataSource
    private var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val holder: ViewHolder
        val viewToReturn: View

        if (convertView != null) {
            viewToReturn = convertView
            holder = viewToReturn.tag as ViewHolder
        } else {
            viewToReturn = inflater.inflate(R.layout.list_item_recipe, parent, false)
            val titleTextView = viewToReturn.findViewById(R.id.recipe_list_title) as TextView
            val subtitleTextView = viewToReturn.findViewById(R.id.recipe_list_subtitle) as TextView
            val detailTextView = viewToReturn.findViewById(R.id.recipe_list_detail) as TextView
            val thumbnailImageView = viewToReturn.findViewById(R.id.recipe_list_thumbnail) as ImageView

            holder = ViewHolder(titleTextView,
                    subtitleTextView,
                    detailTextView,
                    thumbnailImageView)

            viewToReturn.tag = holder
        }

        val titleTextView = holder.titleTextView
        val subtitleTextView = holder.subtitleTextView
        val detailTextView = holder.detailTextView
        val thumbnailImageView = holder.thumbnailImageView

        val recipe = getItem(position) as Recipe
        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.description
        detailTextView.text = recipe.label

        Picasso.with(context).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        return viewToReturn
    }
}

private class ViewHolder(val titleTextView: TextView,
                         val subtitleTextView: TextView,
                         val detailTextView: TextView,
                         val thumbnailImageView: ImageView)