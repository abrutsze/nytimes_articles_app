package com.nytimes.articleapp.article.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nytimes.articleapp.article.view.fragments.EmailedFragment
import com.nytimes.articleapp.article.view.fragments.SharedFragment
import com.nytimes.articleapp.article.view.fragments.ViewedFragment

class TabAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
               return EmailedFragment()
            }
            1 -> {
                return SharedFragment()
            }
            2 -> {
              return ViewedFragment()
            }
            else -> return null
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}