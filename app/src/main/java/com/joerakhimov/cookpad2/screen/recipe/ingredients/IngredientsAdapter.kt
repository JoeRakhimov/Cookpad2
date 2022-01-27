package com.joerakhimov.cookpad2.screen.recipe.ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joerakhimov.cookpad2.databinding.ListitemIngredientBinding
import com.joerakhimov.cookpad2.extensions.showOrGone

class IngredientsAdapter(private val ingredients: List<String>) :
    RecyclerView.Adapter<IngredientsAdapter.IngredientHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val itemBinding = ListitemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.bind(ingredient, itemCount)
    }

    override fun getItemCount(): Int = ingredients.size

    class IngredientHolder(private val itemBinding: ListitemIngredientBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(ingredient: String, itemCount: Int) {
            itemBinding.textIngredient.text = ingredient
            val showDivider = position != itemCount - 1
            itemBinding.viewIngredientDivider.showOrGone(showDivider)
        }
    }

}