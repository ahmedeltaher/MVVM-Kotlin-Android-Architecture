package com.task.ui.component.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.data.DataRepository
import com.task.data.Resource
import com.task.data.dto.recipes.Recipes
import com.task.data.error.NETWORK_ERROR
import com.util.InstantExecutorExtension
import com.util.MainCoroutineRule
import com.util.TestModelsGenerator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class RecipesListViewModelTest {
    // Subject under test
    private lateinit var recipesListViewModel: RecipesListViewModel

    // Use a fake UseCase to be injected into the viewModel
    private val dataRepository: DataRepository = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var recipeTitle: String
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Before
    fun setUp() {
        // Create class under test
        // We initialise the repository with no tasks
        recipeTitle = testModelsGenerator.getStubSearchTitle()
    }

    @Test
    fun `get Recipes List`() {
        // Let's do an answer for the liveData
        val recipesModel = testModelsGenerator.generateRecipes()

        //1- Mock calls
        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(Resource.Success(recipesModel))
        }

        //2-Call
        recipesListViewModel = RecipesListViewModel(dataRepository)
        recipesListViewModel.getRecipes()
        //active observer for livedata
        recipesListViewModel.recipesLiveData.observeForever { }

        //3-verify
        val isEmptyList = recipesListViewModel.recipesLiveData.value?.data?.recipesList.isNullOrEmpty()
        assertEquals(recipesModel, recipesListViewModel.recipesLiveData.value?.data)
        assertEquals(false,isEmptyList)
    }

    @Test
    fun `get Recipes Empty List`() {
        // Let's do an answer for the liveData
        val recipesModel = testModelsGenerator.generateRecipesModelWithEmptyList()

        //1- Mock calls
        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(Resource.Success(recipesModel))
        }

        //2-Call
        recipesListViewModel = RecipesListViewModel(dataRepository)
        recipesListViewModel.getRecipes()
        //active observer for livedata
        recipesListViewModel.recipesLiveData.observeForever { }

        //3-verify
        val isEmptyList = recipesListViewModel.recipesLiveData.value?.data?.recipesList.isNullOrEmpty()
        assertEquals(recipesModel, recipesListViewModel.recipesLiveData.value?.data)
        assertEquals(true, isEmptyList)
    }

    @Test
    fun `get Recipes Error`() {
        // Let's do an answer for the liveData
        val error: Resource<Recipes> = Resource.DataError(NETWORK_ERROR)

        //1- Mock calls
        coEvery { dataRepository.requestRecipes() } returns flow {
            emit(error)
        }

        //2-Call
        recipesListViewModel = RecipesListViewModel(dataRepository)
        recipesListViewModel.getRecipes()
        //active observer for livedata
        recipesListViewModel.recipesLiveData.observeForever { }

        //3-verify
        assertEquals(NETWORK_ERROR, recipesListViewModel.recipesLiveData.value?.errorCode)
    }

    @Test
    fun `search Success`() {
        // Let's do an answer for the liveData
        val recipe = testModelsGenerator.generateRecipesItemModel()
        val title = recipe.name
        //1- Mock calls
        recipesListViewModel = RecipesListViewModel(dataRepository)
        recipesListViewModel.recipesLiveDataPrivate.value = Resource.Success(testModelsGenerator.generateRecipes())

        //2-Call
        recipesListViewModel.onSearchClick(title)
        //active observer for livedata
        recipesListViewModel.recipeSearchFound.observeForever { }

        //3-verify
        assertEquals(recipe, recipesListViewModel.recipeSearchFound.value)
    }

    @Test
    fun `search Fail`() {
        // Let's do an answer for the liveData
        val title = "*&*^%"

        //1- Mock calls
        recipesListViewModel = RecipesListViewModel(dataRepository)
        recipesListViewModel.recipesLiveDataPrivate.value = Resource.Success(testModelsGenerator.generateRecipes())

        //2-Call
        recipesListViewModel.onSearchClick(title)
        //active observer for livedata
        recipesListViewModel.noSearchFound.observeForever { }

        //3-verify
        assertEquals(Unit, recipesListViewModel.noSearchFound.value)
    }
}
