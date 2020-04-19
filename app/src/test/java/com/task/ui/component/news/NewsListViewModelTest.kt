package com.task.ui.component.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.task.data.Resource
import com.task.data.error.Error
import com.task.data.remote.dto.NewsModel
import com.task.usecase.NewsUseCase
import com.util.InstantExecutorExtension
import com.util.MainCoroutineRule
import com.util.TestModelsGenerator
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    private val newsUseCase: NewsUseCase = mockk()

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
        val newsModelSuccess = MutableLiveData<Resource<NewsModel>>()
        every { newsUseCase.newsLiveData } returns newsModelSuccess
    }

    @Test
    fun handleNewsList() {
        // Let's do an answer for the liveData
        val newsModeltest = testModelsGenerator.generateNewsModel()
        val newsModelSuccess = MutableLiveData<Resource<NewsModel>>()
        newsModelSuccess.value = Resource.Success(newsModeltest)

        //1- Mock calls
        every { newsUseCase.getNews() } just Runs
        every { newsUseCase.newsLiveData } returns newsModelSuccess

        //2-Call
        newsListViewModel = NewsListViewModel(newsUseCase)
        newsListViewModel.getNews()
        //active observer for livedata
        newsUseCase.newsLiveData.observeForever { }

        //3-verify
        val isEmptyList = newsListViewModel.newsLiveData.value?.data?.newsItems.isNullOrEmpty()
        assert(newsModeltest == newsListViewModel.newsLiveData.value?.data)
        assert(!isEmptyList)
    }

    @Test
    fun handleEmptyList() {
        // Let's do an answer for the liveData
        val newsModeltest = testModelsGenerator.generateNewsModelWithEmptyList()
        val newsModelSuccess = MutableLiveData<Resource<NewsModel>>()
        newsModelSuccess.value = Resource.Success(newsModeltest)

        //1- Mock calls
        every { newsUseCase.getNews() } just Runs
        every { newsUseCase.newsLiveData } returns newsModelSuccess

        //2-Call
        newsListViewModel = NewsListViewModel(newsUseCase)
        newsListViewModel.getNews()
        //active observer for livedata
        newsUseCase.newsLiveData.observeForever { }

        //3-verify
        val isEmptyList = newsListViewModel.newsLiveData.value?.data?.newsItems.isNullOrEmpty()
        assert(newsModeltest == newsListViewModel.newsLiveData.value?.data)
        assert(isEmptyList)
    }

    @Test
    fun handleNewsError() {
        // Let's do an answer for the liveData
        val newsModelFail = MutableLiveData<Resource<NewsModel>>()
        newsModelFail.value = Resource.DataError(Error.NETWORK_ERROR)

        //1- Mock calls
        every { newsUseCase.getNews() } just Runs
        every { newsUseCase.newsLiveData } returns newsModelFail

        //2-Call
        newsListViewModel = NewsListViewModel(newsUseCase)
        newsListViewModel.getNews()
        //active observer for livedata
        newsUseCase.newsLiveData.observeForever { }

        //3-verify
        assert(Error.NETWORK_ERROR == newsListViewModel.newsLiveData.value?.errorCode)
    }

    @Test
    fun testSearchSuccess() {

        // Let's do an answer for the liveData
        val newsItem = testModelsGenerator.generateNewsItemModel()
        val title = newsItem.title
        //1- Mock calls
        every { newsUseCase.searchByTitle(title) } returns newsItem

        //2-Call
        newsListViewModel = NewsListViewModel(newsUseCase)
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
        every { newsUseCase.searchByTitle(title) } returns null

        //2-Call
        newsListViewModel = NewsListViewModel(newsUseCase)
        newsListViewModel.onSearchClick(title)
        //active observer for livedata
        newsListViewModel.noSearchFound.observeForever { }

        //3-verify
        assert(newsListViewModel.noSearchFound.value == Unit)
    }
}