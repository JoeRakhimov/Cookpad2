package com.joerakhimov.cookpad2.data.api

import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import retrofit2.http.GET

interface ApiService {

    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>

}