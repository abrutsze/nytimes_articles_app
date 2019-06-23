package com.nytimes.articleapp.article.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.nytimes.articleapp.article.R
import com.nytimes.articleapp.article.di.component.DIComponent
import com.nytimes.articleapp.article.view.adapters.TabAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity() ,DIComponent.Injectable{


    override fun inject(diComponent: DIComponent) {
        diComponent.inject(this)
    }

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)


        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager = findViewById<ViewPager>(R.id.viewPager)


        tabLayout!!.addTab(tabLayout!!.newTab().setText("Emailed"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Shared"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Viewed"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter =
            TabAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
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

        saved.setOnClickListener {
            val intent = SavedActivity.newIntent(this)
            startActivity(intent)
        }

    }

}
