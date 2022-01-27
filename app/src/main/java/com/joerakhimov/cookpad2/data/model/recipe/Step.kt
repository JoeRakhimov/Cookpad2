package com.joerakhimov.cookpad2.data.model.recipe

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Step(

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("image_urls")
    val imageUrls: List<String>,

    ): Serializable