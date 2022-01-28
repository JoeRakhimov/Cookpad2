package com.joerakhimov.cookpad2.screen.recipes

import app.cash.turbine.test
import com.joerakhimov.cookpad2.data.model.recipe.Recipe
import com.joerakhimov.cookpad2.data.model.util.Status
import com.joerakhimov.cookpad2.usecase.GetRecipesUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class RecipesViewModelTest{

    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    private fun setupViewModel(getRecipesUseCase: GetRecipesUseCase = mock(GetRecipesUseCase::class.java)): RecipesViewModel {
        this.getRecipesUseCase = getRecipesUseCase
        return RecipesViewModel(this.getRecipesUseCase)
    }

    @Test
    fun givenServerResponse200_whenGetRecipes_shouldReturnSuccess() {
        val viewModel =
            setupViewModel(
                getRecipesUseCase = mock(GetRecipesUseCase::class.java) {
                    emptyList<Recipe>()
                }
            )
        runBlockingTest {
            viewModel.recipes.test {
                viewModel.getRecipes()
                assertEquals(Status.SUCCESS, awaitItem().status)
            }
        }
    }

    @Test
    fun givenServerResponseError_whenGetRecipes_shouldReturnError() {
        val viewModel =
            setupViewModel(
                getRecipesUseCase = mock(GetRecipesUseCase::class.java) {
                    throw Exception("Response error")
                }
            )
        runBlockingTest {
            viewModel.recipes.test {
                viewModel.getRecipes()
                assertEquals(Status.ERROR, awaitItem().status)
            }
        }
    }

}