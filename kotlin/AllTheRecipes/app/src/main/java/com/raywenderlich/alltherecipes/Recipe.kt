package com.raywenderlich.alltherecipes

import android.content.Context
import org.json.JSONObject

/**
 * Created by pietrorea on 10/22/17.
 */

class Recipe(val title: String,
             val description: String,
             val imageUrl: String,
             val instructionUrl: String,
             val label: String) {

    companion object {

        @JvmStatic fun getRecipesFromFile(fileName: String, context: Context): ArrayList<Recipe> {
            var recipeList = ArrayList<Recipe>()

            val jsonString = loadJsonFromAsset("recipes.json", context)
            val json = JSONObject(jsonString)
            val recipes = json.getJSONArray("recipes")

            for (i in 0 until recipes.length()) {

                val recipeJson = recipes.getJSONObject(i)
                val recipe = Recipe(
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