package com.nytimes.articleapp.article.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nytimes.articleapp.article.view.fragments.EmailSavedFragment
import com.nytimes.articleapp.article.view.fragments.ShareSavedFragment
import com.nytimes.articleapp.article.view.fragments.ViewSavedFragment


class SavedAdapter (private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                return EmailSavedFragment()
            }
            1 -> {
                return ShareSavedFragment()
            }
            2 -> {
                return ViewSavedFragment()
            }
            else -> return null
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}