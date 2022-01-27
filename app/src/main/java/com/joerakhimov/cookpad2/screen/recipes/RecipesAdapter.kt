package com.joerakhimov.cookpad2.screen.recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joerakhimov.cookpad2.R
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.databinding.ListitemRecipeBinding
import com.joerakhimov.cookpad2.screen.recipe.ARG_RECIPE
import javax.inject.Inject

class RecipesAdapter @Inject constructor(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipesAdapter.RecipeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val itemBinding =
            ListitemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        val recipe: Recipe = recipes[position]
        holder.bind(recipe, holder.itemView)
    }

    override fun getItemCount(): Int = recipes.size

    class RecipeHolder(private val itemBinding: ListitemRecipeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(recipe: Recipe, view: View) {
            itemBinding.textRecipeTitle.text = recipe.title
            Glide.with(view.context).load(recipe.imageUrl).error(R.drawable.placeholder)
                .into(itemBinding.imageRecipePhoto)
            view.setOnClickListener {
                val bundle = bundleOf(ARG_RECIPE to recipe)
                view.findNavController().navigate(R.id.fragment_recipe, bundle)
            }
        }
    }

}