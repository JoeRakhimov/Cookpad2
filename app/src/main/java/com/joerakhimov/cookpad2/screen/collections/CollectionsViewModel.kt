package com.joerakhimov.cookpad2.screen.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joerakhimov.cookpad2.data.api.Resource
import com.joerakhimov.cookpad2.data.model.collection.CollectionItem
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.usecase.GetCollectionsUseCase
import com.joerakhimov.cookpad2.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(
    private val getCollectionsUseCase: GetCollectionsUseCase
) : ViewModel() {

    private val _collections = MutableLiveData<Resource<List<CollectionItem>>>()
    val collections: LiveData<Resource<List<CollectionItem>>> = _collections

    init {
        getCollections()
    }

    private fun getCollections() {
        viewModelScope.launch {
            _collections.value = Resource.loading(data = null)
            try {
                _collections.value = Resource.success(data = getCollectionsUseCase())
            } catch (exception: Exception) {
                _collections.value =
                    Resource.error(data = null, message = exception.message ?: "Error Occurred!")
            }
        }
    }

    fun onRefresh() {
        getCollections()
    }

}