package com.joerakhimov.cookpad2.data.model.recipe

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    ): Serializable