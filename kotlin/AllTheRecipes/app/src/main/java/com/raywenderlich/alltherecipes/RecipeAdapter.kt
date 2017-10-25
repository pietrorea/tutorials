package com.raywenderlich.alltherecipes

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.util.HashMap

/**
 * Created by pietrorea on 10/22/17.
 */

class RecipeAdapter(context: Context, dataSource: ArrayList<Recipe>): BaseAdapter() {

    companion object {
        private val LABEL_COLORS: HashMap<String, Int> =
                hashMapOf("Low-Carb" to R.color.colorLowCarb,
                        "Low-Fat" to R.color.colorLowFat,
                        "Low-Sodium" to R.color.colorLowSodium,
                        "Vegetarian" to R.color.colorVegetarian,
                        "Balance" to  R.color.colorBalanced)
    }

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

        val titleTypeFace = Typeface.createFromAsset(context.assets,  "fonts/JosefinSans-Bold.ttf")
        titleTextView.typeface = titleTypeFace

        val subtitleTypeFace = Typeface.createFromAsset(context.assets, "fonts/JosefinSans-SemiBoldItalic.ttf")
        subtitleTextView.typeface = subtitleTypeFace

        val detailTypeFace = Typeface.createFromAsset(context.assets, "fonts/Quicksand-Bold.otf")
        detailTextView.typeface = detailTypeFace

        val colorId: Int? = LABEL_COLORS.get(recipe.label)
        if (colorId != null) {
            detailTextView.setTextColor(ContextCompat.getColor(context, colorId!!))
        }

        return viewToReturn
    }
}

private class ViewHolder(val titleTextView: TextView,
                         val subtitleTextView: TextView,
                         val detailTextView: TextView,
                         val thumbnailImageView: ImageView)