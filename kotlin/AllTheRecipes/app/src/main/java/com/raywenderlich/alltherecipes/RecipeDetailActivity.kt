package com.raywenderlich.alltherecipes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class RecipeDetailActivity : AppCompatActivity() {

    private var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val title = intent.extras.getString("title")
        val url = intent.extras.getString("url")
        setTitle(title)

        mWebView = findViewById(R.id.detail_web_view) as WebView
        mWebView?.loadUrl(url)
    }
}
