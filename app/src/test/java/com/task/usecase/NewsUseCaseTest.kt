package com.task.usecase


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
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by ahmedeltaher on 3/8/17.
 */
@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class NewsUseCaseTest {

    private var dataRepository: DataRepository? = null

    private lateinit var newsUseCase: NewsUseCase
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()
    private lateinit var newsModel: NewsModel

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @BeforeEach
    fun setUp() {
        dataRepository = DataRepository(mockk(), mockk())
        newsUseCase = NewsUseCase(dataRepository!!, mainCoroutineRule.coroutineContext)
    }

    @Test
    fun testGetNewsSuccessful() {
        //mock
        newsModel = testModelsGenerator.generateNewsModel()
        val serviceResponse = Resource.Success(newsModel)
        coEvery { dataRepository?.requestNews() } returns serviceResponse
        //call
        newsUseCase.getNews()
        newsUseCase.newsLiveData.observeForever { }
        //assert test
        assert(serviceResponse == newsUseCase.newsLiveData.value)
    }

    @Test
    fun testGetNewsFail() {
        //mock
        val serviceResponse = Resource.DataError<NewsModel>(Error.DEFAULT_ERROR)
        coEvery { dataRepository?.requestNews() } returns serviceResponse
        //call
        newsUseCase.getNews()
        newsUseCase.newsLiveData.observeForever {  }
        //assert test
        assert(Error.DEFAULT_ERROR == newsUseCase.newsLiveData.value?.errorCode)
    }

    @Test
    fun searchByTitleSuccess() {
        newsModel = testModelsGenerator.generateNewsModel()
        val title = newsModel.newsItems.last().title
        val serviceResponse = Resource.Success(newsModel)
        coEvery { dataRepository?.requestNews() } returns serviceResponse
        //call
        newsUseCase.getNews()
        val newsItem = newsUseCase.searchByTitle(title)
        assertNotNull(newsItem)
        assert(newsItem?.title == newsItem?.title)
    }

    @Test
    fun searchByTitleFail() {
        val stup = "&%$##"
        newsModel = testModelsGenerator.generateNewsModel()
        val serviceResponse = Resource.Success(newsModel)
        coEvery { dataRepository?.requestNews() } returns serviceResponse
        //call
        val newsItem = newsUseCase.searchByTitle(stup)
        assert(newsItem == null)
    }

    @AfterEach
    fun tearDown() {
    }
}
