/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.alltherecipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import android.widget.*;
import android.content.Intent;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

  private ListView mListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mListView = (ListView) findViewById(R.id.recipe_list_view);
    final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
    String[] listItems = new String[recipeList.size()];

    for (int i = 0; i < recipeList.size(); i++) {
      Recipe recipe = recipeList.get(i);
      listItems[i] = recipe.getTitle();
    }

    RecipeAdapter adapter = new RecipeAdapter(this, recipeList);
    mListView.setAdapter(adapter);

      final Context context = this;
      mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              Recipe selectedRecipe = recipeList.get(position);
              Intent detailIntent = new Intent(context, RecipeDetailActivity.class);
              detailIntent.putExtra("title", selectedRecipe.getTitle());
              detailIntent.putExtra("url", selectedRecipe.getInstructionUrl());

              startActivity(detailIntent);
          }

      });
  }

}
