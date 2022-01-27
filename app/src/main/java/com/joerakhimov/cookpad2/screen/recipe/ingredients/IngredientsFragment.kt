package com.joerakhimov.cookpad2.screen.recipe.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.databinding.FragmentIngredientsBinding
import com.joerakhimov.cookpad2.databinding.FragmentRecipesBinding
import com.joerakhimov.cookpad2.screen.recipe.ARG_RECIPE
import timber.log.Timber

class IngredientsFragment: Fragment() {

    lateinit var recipe: Recipe
    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance(recipe: Recipe): IngredientsFragment {
            return IngredientsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_RECIPE, recipe)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = arguments?.getSerializable(ARG_RECIPE) as Recipe
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showIngredients()
    }

    private fun showIngredients(){
        binding.recyclerIngredients.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = IngredientsAdapter(recipe.ingredients)
        }
    }

}