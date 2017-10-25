package com.raywenderlich.alltherecipes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.recipe_list_view) as ListView
        val recipeList = Recipe.getRecipesFromFile("recipes.json", this)
        var listItems = ArrayList<String>()

        for (recipe: Recipe in recipeList) {
            listItems.add(recipe.title)
        }

        val adapter = RecipeAdapter(this, recipeList)
        listView?.adapter = adapter

        listView?.setOnItemClickListener { adapterView, view, position, id ->
            val selectedRecipe = recipeList.get(position)
            val detailIntent = Intent(this, RecipeDetailActivity::class.java)
            detailIntent.putExtra("title", selectedRecipe.title)
            detailIntent.putExtra("url", selectedRecipe.instructionUrl)

            startActivity(detailIntent)
        }
    }
}
