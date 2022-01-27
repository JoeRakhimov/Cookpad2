package com.joerakhimov.cookpad2.usecase

import com.joerakhimov.cookpad2.data.api.ApiService
import com.joerakhimov.cookpad2.data.model.collection.CollectionItem
import javax.inject.Inject

class GetCollectionsUseCase @Inject constructor(private val api: ApiService) {

    suspend operator fun invoke(): List<CollectionItem>{
        return api.getCollections()
    }

}