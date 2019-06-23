package com.nytimes.articleapp.article.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.nytimes.articleapp.article.R
import com.nytimes.articleapp.article.view.adapters.SavedAdapter
import com.google.android.material.tabs.TabLayout

class SavedActivity : AppCompatActivity() {

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, SavedActivity::class.java)
        }
    }
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_saved)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager>(R.id.viewPager)


        tabLayout!!.addTab(tabLayout!!.newTab().setText("Emailed"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Shared"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Viewed"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter =
            SavedAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })



    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
