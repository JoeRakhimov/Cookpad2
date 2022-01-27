package com.joerakhimov.cookpad2.data.model.recipe

import com.google.gson.annotations.SerializedName
import com.joerakhimov.cookpad2.data.model.recipe.User
import java.io.Serializable

data class Recipe(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("story")
    val story: String,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("published_at")
    val publishedAt: String,

    @field:SerializedName("user")
    val user: User,

    @field:SerializedName("ingredients")
    val ingredients: List<String>,

    @field:SerializedName("steps")
    val steps: List<Step>,

    ): Serializable
