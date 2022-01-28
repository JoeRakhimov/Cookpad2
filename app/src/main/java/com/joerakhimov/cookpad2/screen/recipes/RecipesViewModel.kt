package com.joerakhimov.cookpad2.screen.recipes

import androidx.lifecycle.*
import com.joerakhimov.cookpad2.data.model.util.Resource
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _recipes = MutableLiveData<Resource<List<Recipe>>>()
    val recipes: LiveData<Resource<List<Recipe>>> = _recipes

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            _recipes.value = Resource.loading(data = null)
            try {
                _recipes.value = Resource.success(data = getRecipesUseCase())
            } catch (exception: Exception) {
                _recipes.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }
    }

    fun onRefresh() {
        getRecipes()
    }

}