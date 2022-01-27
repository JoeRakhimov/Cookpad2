package com.joerakhimov.cookpad2.screen.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.databinding.FragmentMainBinding
import com.joerakhimov.cookpad2.databinding.FragmentRecipeBinding
import com.joerakhimov.cookpad2.databinding.FragmentRecipesBinding
import com.joerakhimov.cookpad2.screen.main.MainPagerAdapter
import timber.log.Timber

const val ARG_RECIPE = "recipe"

class RecipeFragment: Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = arguments?.getSerializable(ARG_RECIPE) as Recipe
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = recipe.title
        initTabs()
    }

    private fun initTabs() {
        val pagerAdapter = RecipePagerAdapter(context, parentFragmentManager, recipe)
        val viewPager: ViewPager = binding.pagerRecipe
        viewPager.adapter = pagerAdapter
        val tabs: TabLayout = binding.tabsRecipe
        tabs.setupWithViewPager(viewPager)
    }

}