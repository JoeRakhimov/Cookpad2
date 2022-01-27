package com.joerakhimov.cookpad2.data.api

import com.joerakhimov.cookpad2.data.model.collection.CollectionItem
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("collections")
    suspend fun getCollections(): List<CollectionItem>

    @GET("collections/{collection_id}/recipes")
    suspend fun getRecipesByCollectionId(@Path("collection_id") collectionId: Int): List<Recipe>

}