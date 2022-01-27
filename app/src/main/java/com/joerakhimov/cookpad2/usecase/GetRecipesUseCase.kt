package com.joerakhimov.cookpad2.usecase

import com.joerakhimov.cookpad2.data.api.ApiService
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import timber.log.Timber
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val api: ApiService) {

    suspend operator fun invoke(): List<Recipe>{
        return api.getRecipes()
    }

}