package com.joerakhimov.cookpad2.data.model.collection

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CollectionItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("recipe_count")
    val recipeCount: Int,

    @field:SerializedName("preview_image_urls")
    val previewImageUrls: List<String>

): Serializable