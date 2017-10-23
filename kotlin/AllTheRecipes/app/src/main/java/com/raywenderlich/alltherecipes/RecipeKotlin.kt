package com.raywenderlich.alltherecipes

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

/**
 * Created by pietrorea on 10/22/17.
 */

class RecipeKotlin(val title: String,
                   val description: String,
                   val imageUrl: String,
                   val instructionUrl: String,
                   val label: String) {

    companion object {

        @JvmStatic fun getRecipesFromFile(fileName: String, context: Context): ArrayList<RecipeKotlin> {
            var recipeList = ArrayList<RecipeKotlin>()

            val jsonString = loadJsonFromAsset("recipes.json", context)
            val json = JSONObject(jsonString)
            val recipes = json.getJSONArray("recipes")

            for (i in 0..recipes.length() - 1) {

                val recipeJson = recipes.getJSONObject(i)
                val recipe = RecipeKotlin(
                        recipeJson.getString("title"),
                        recipeJson.getString("description"),
                        recipeJson.getString("image"),
                        recipeJson.getString("url"),
                        recipeJson.getString("dietLabel")
                )

                recipeList.add(recipe)
            }

            return recipeList
        }

        fun loadJsonFromAsset(fileName: String, context: Context): String {

            val inputStream = context.assets.open(fileName)

            val inputAsString = inputStream.bufferedReader().use {
                it.readText()
            }

            return inputAsString
        }
    }


}