package com.joerakhimov.cookpad2.screen.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.joerakhimov.cookpad2.R

private val TAB_TITLES = arrayOf(
    R.string.recipes,
    R.string.collections
)

class MainPagerAdapter(private val context: Context?, fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context?.resources?.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }

}