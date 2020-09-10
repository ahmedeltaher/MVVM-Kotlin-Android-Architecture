package com.task.ui.component.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.data.DataRepository
import com.task.data.Resource
import com.util.InstantExecutorExtension
import com.util.MainCoroutineRule
import com.util.TestModelsGenerator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by AhmedEltaher
 */
@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class DetailsViewModelTest {
    // Subject under test
    private lateinit var detailsViewModel: DetailsViewModel

    // Use a fake UseCase to be injected into the viewModel
    private val dataRepository: DataRepository = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Test
    fun `init Intent Data`() {
        //1- Mock Data
        val recipesItem = testModelsGenerator.generateRecipesItemModel()
        //2-Call
        detailsViewModel = DetailsViewModel(dataRepository)
        detailsViewModel.initIntentData(recipesItem)
        //active observer for livedata
        detailsViewModel.recipeData.observeForever { }

        //3-verify
        val recipesData = detailsViewModel.recipeData.value
        assertEquals(recipesItem, recipesData)
    }

    @Test
    fun `add Recipe To Favourites`() {
        //1- Mock calls
        val isFavourites = true
        val recipesItem = testModelsGenerator.generateRecipesItemModel()
        coEvery { dataRepository.addToFavourite(recipesItem.id) } returns flow {
            emit(Resource.Success(true))
        }
        //2-Call
        detailsViewModel = DetailsViewModel(dataRepository)
        detailsViewModel.recipePrivate.value = recipesItem
        detailsViewModel.addToFavourites()
        //active observer for livedata
        detailsViewModel.isFavourite.observeForever { }

        //3-verify
        val recipesData = detailsViewModel.isFavourite.value
        assertEquals(isFavourites, recipesData?.data)
    }

    @Test
    fun `remove Recipe From Favourites`() {
        //1- Mock calls
        val isFavourites = false
        val recipesItem = testModelsGenerator.generateRecipesItemModel()
        coEvery { dataRepository.removeFromFavourite(recipesItem.id) } returns flow {
            emit(Resource.Success(true))
        }
        //2-Call
        detailsViewModel = DetailsViewModel(dataRepository)
        detailsViewModel.recipePrivate.value = recipesItem
        detailsViewModel.removeFromFavourites()
        //active observer for livedata
        detailsViewModel.isFavourite.observeForever { }

        //3-verify
        val recipesData = detailsViewModel.isFavourite.value
        assertEquals(isFavourites, recipesData?.data)
    }

    @Test
    fun `is Favourite Recipe`() {
        //1- Mock calls
        val isFavourites = true
        val recipesItem = testModelsGenerator.generateRecipesItemModel()
        coEvery { dataRepository.isFavourite(recipesItem.id) } returns flow {
            emit(Resource.Success(true))
        }
        //2-Call
        detailsViewModel = DetailsViewModel(dataRepository)
        detailsViewModel.recipePrivate.value = recipesItem
        detailsViewModel.isFavourites()
        //active observer for livedata
        detailsViewModel.isFavourite.observeForever { }

        //3-verify
        val recipesData = detailsViewModel.isFavourite.value
        assertEquals(isFavourites, recipesData?.data)
    }
}
