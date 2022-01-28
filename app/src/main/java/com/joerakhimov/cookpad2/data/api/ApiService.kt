package com.joerakhimov.cookpad2.data.api

import com.joerakhimov.cookpad2.data.model.collection.CollectionItem
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>

}