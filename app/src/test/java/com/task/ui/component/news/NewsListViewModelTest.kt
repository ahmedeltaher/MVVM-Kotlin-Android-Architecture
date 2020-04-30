package com.task.ui.component.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.data.DataRepository
import com.task.data.Resource
import com.task.data.error.Error
import com.task.data.remote.dto.NewsModel
import com.util.InstantExecutorExtension
import com.util.MainCoroutineRule
import com.util.TestModelsGenerator
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class NewsListViewModelTest {
    // Subject under test
    private lateinit var newsListViewModel: NewsListViewModel

    // Use a fake UseCase to be injected into the viewmodel
    private val dataRepository: DataRepository = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var newsTitle: String
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Before
    fun setUp() {
        // Create class under test
        // We initialise the repository with no tasks
        newsTitle = testModelsGenerator.getStupSearchTitle()
    }

    @Test
    fun handleNewsList() {
        // Let's do an answer for the liveData
        val newsModeltest = testModelsGenerator.generateNewsModel()

        //1- Mock calls
        coEvery { dataRepository.requestNews() } returns flow {
            emit(Resource.Success(newsModeltest))
        }

        //2-Call
        newsListViewModel = NewsListViewModel(dataRepository)
        newsListViewModel.getNews()
        //active observer for livedata
        newsListViewModel.newsLiveData.observeForever { }

        //3-verify
        val isEmptyList = newsListViewModel.newsLiveData.value?.data?.results.isNullOrEmpty()
        assert(newsModeltest == newsListViewModel.newsLiveData.value?.data)
        assert(!isEmptyList)
    }

    @Test
    fun handleEmptyList() {
        // Let's do an answer for the liveData
        val newsModeltest = testModelsGenerator.generateNewsModelWithEmptyList()

        //1- Mock calls
        coEvery { dataRepository.requestNews() } returns flow {
            emit(Resource.Success(newsModeltest))
        }

        //2-Call
        newsListViewModel = NewsListViewModel(dataRepository)
        newsListViewModel.getNews()
        //active observer for livedata
        newsListViewModel.newsLiveData.observeForever { }

        //3-verify
        val isEmptyList = newsListViewModel.newsLiveData.value?.data?.results.isNullOrEmpty()
        assert(newsModeltest == newsListViewModel.newsLiveData.value?.data)
        assert(isEmptyList)
    }

    @Test
    fun handleNewsError() {
        // Let's do an answer for the liveData
        val error: Resource<NewsModel> = Resource.DataError(Error.NETWORK_ERROR)

        //1- Mock calls
        coEvery { dataRepository.requestNews() } returns flow {
            emit(error)
        }

        //2-Call
        newsListViewModel = NewsListViewModel(dataRepository)
        newsListViewModel.getNews()
        //active observer for livedata
        newsListViewModel.newsLiveData.observeForever { }

        //3-verify
        assert(Error.NETWORK_ERROR == newsListViewModel.newsLiveData.value?.errorCode)
    }

    @Test
    fun testSearchSuccess() {
        // Let's do an answer for the liveData
        val newsItem = testModelsGenerator.generateNewsItemModel()
        val title = newsItem.title
        //1- Mock calls
        newsListViewModel = NewsListViewModel(dataRepository)
        newsListViewModel._newsLiveData.value = Resource.Success(testModelsGenerator.generateNewsModel())

        //2-Call
        newsListViewModel.onSearchClick(title)
        //active observer for livedata
        newsListViewModel.newsSearchFound.observeForever { }

        //3-verify
        assert(newsListViewModel.newsSearchFound.value == newsItem)
    }

    @Test
    fun testSearchFail() {
        // Let's do an answer for the liveData
        val title = "*&*^%"

        //1- Mock calls
        newsListViewModel = NewsListViewModel(dataRepository)
        newsListViewModel._newsLiveData.value = Resource.Success(testModelsGenerator.generateNewsModel())

        //2-Call
        newsListViewModel.onSearchClick(title)
        //active observer for livedata
        newsListViewModel.noSearchFound.observeForever { }

        //3-verify
        assert(newsListViewModel.noSearchFound.value == Unit)
    }
}