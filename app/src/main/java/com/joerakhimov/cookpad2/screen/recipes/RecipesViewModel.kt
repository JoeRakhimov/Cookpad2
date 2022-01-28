package com.joerakhimov.cookpad2.screen.recipes

import androidx.lifecycle.*
import com.joerakhimov.cookpad2.data.model.util.Resource
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _recipes = MutableStateFlow<Resource<List<Recipe>>>(Resource.loading(data = null))
    val recipes: StateFlow<Resource<List<Recipe>>> = _recipes

    init {
        getRecipes()
    }

    fun getRecipes() {
        viewModelScope.launch {
            _recipes.emit(Resource.loading(data = null))
            try {
                val recipes = getRecipesUseCase()
                _recipes.emit(Resource.success(data = recipes))
            } catch (exception: Exception) {
                _recipes.emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
    }

    fun onRefresh() {
        getRecipes()
    }

}