package com.joerakhimov.cookpad2.screen.collections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joerakhimov.cookpad2.R
import com.joerakhimov.cookpad2.data.model.collection.CollectionItem
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.databinding.ListitemCollectionBinding
import com.joerakhimov.cookpad2.databinding.ListitemRecipeBinding
import com.joerakhimov.cookpad2.screen.recipe.ARG_RECIPE
import com.joerakhimov.cookpad2.screen.recipes.ARG_COLLECTION
import javax.inject.Inject

class CollectionsAdapter @Inject constructor(private val collections: List<CollectionItem>) :
    RecyclerView.Adapter<CollectionsAdapter.RecipeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val itemBinding =
            ListitemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        val collection: CollectionItem = collections[position]
        holder.bind(collection, holder.itemView)
    }

    override fun getItemCount(): Int = collections.size

    class RecipeHolder(private val itemBinding: ListitemCollectionBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(collection: CollectionItem, view: View) {
            itemBinding.textCollectionTitle.text = collection.title
            itemBinding.textRecipesCount.text = when (collection.recipeCount) {
                0 -> view.context.getString(R.string.no_recipe)
                1 -> view.context.getString(R.string.one_recipe)
                else -> String.format(view.context.getString(R.string.recipes), collection.recipeCount)
            }
            view.setOnClickListener {
                val bundle = bundleOf(ARG_COLLECTION to collection)
                view.findNavController().navigate(R.id.fragment_recipes, bundle)
            }
        }
    }

}