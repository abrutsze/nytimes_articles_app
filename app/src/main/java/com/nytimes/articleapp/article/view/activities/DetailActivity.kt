package com.nytimes.articleapp.article.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import com.nytimes.articleapp.article.R
import kotlinx.android.synthetic.main.activity_detail.*










class DetailActivity : AppCompatActivity() {


    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        webView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        webView!!.loadUrl(intent.getStringExtra("url"))



        val toolbar = findViewById<Toolbar>(R.id.toolbar_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
