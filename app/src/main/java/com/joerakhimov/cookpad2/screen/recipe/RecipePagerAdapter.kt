package com.joerakhimov.cookpad2.screen.recipe

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.joerakhimov.cookpad2.R
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.screen.recipe.info.InfoFragment
import com.joerakhimov.cookpad2.screen.recipe.ingredients.IngredientsFragment
import com.joerakhimov.cookpad2.screen.recipe.steps.StepsFragment

private val TAB_TITLES = arrayOf(
    R.string.info,
    R.string.ingredients,
    R.string.steps
)

class RecipePagerAdapter(private val context: Context?, fm: FragmentManager, val recipe: Recipe): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> InfoFragment.newInstance(recipe)
            1 -> IngredientsFragment.newInstance(recipe)
            2 -> StepsFragment.newInstance(recipe)
            else -> InfoFragment.newInstance(recipe)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context?.resources?.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }

}