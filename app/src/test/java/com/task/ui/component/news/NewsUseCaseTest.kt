package com.task.ui.component.news

import com.task.data.DataRepository
import com.task.data.remote.Data
import com.task.data.remote.Error
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.NewsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Created by ahmedeltaher on 3/8/17.
 */
@ExperimentalCoroutinesApi
class NewsUseCaseTest {

    private var dataRepository: DataRepository? = null
    private var callback: BaseCallback? = spyk()

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
        newsModel = testModelsGenerator.generateNewsModel()
        val serviceResponse = Data(code = Error.SUCCESS_CODE, data = newsModel)
        coEvery { dataRepository?.requestNews() } returns serviceResponse
        newsUseCase.getNews(callback!!)
        coVerify(exactly = 1, verifyBlock = { callback?.onSuccess(any()) })
        coVerify(exactly = 0, verifyBlock = { callback?.onFail(any()) })
    }

    @Test
    fun testGetNewsFail() {
        val serviceResponse = Data(code = Error.ERROR_CODE, data = null)
        coEvery { dataRepository?.requestNews() } returns serviceResponse
        newsUseCase.getNews(callback!!)
        coVerify(exactly = 0, verifyBlock = { callback?.onSuccess(any()) })
        coVerify(exactly = 1, verifyBlock = { callback?.onFail(any()) })
    }

    @Test
    fun searchByTitleSuccess() {
        val newsItem = newsUseCase.searchByTitle(testModelsGenerator.generateNewsModel().newsItems, testModelsGenerator.getStupSearchTitle())
        assertNotNull(newsItem)
        assertEquals(newsItem?.title, testModelsGenerator.getStupSearchTitle())
    }

    @Test
    fun searchByTitleFail() {
        val stup = "&%$##"
        val newsItem = newsUseCase.searchByTitle(testModelsGenerator.generateNewsModel().newsItems, stup)
        assertEquals(newsItem, null)
    }

    @AfterEach
    fun tearDown() {
    }
}
