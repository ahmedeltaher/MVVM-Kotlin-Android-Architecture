package com.task.ui.component.news

import com.task.data.DataRepository
import com.task.data.remote.ServiceError
import com.task.data.remote.ServiceResponse
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.NewsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by ahmedeltaher on 3/8/17.
 */

@ExtendWith(MockKExtension::class)
class NewsUseCaseTest {

    private var dataRepository: DataRepository? = null
    private var callback: BaseCallback? = spyk()

    private lateinit var newsUseCase: NewsUseCase
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()
    private lateinit var newsModel: NewsModel

    @BeforeEach
    fun setUp() {
        dataRepository = DataRepository(mockk(), mockk())
        newsUseCase = NewsUseCase(dataRepository!!,Dispatchers.IO)
    }

    @Test
    fun testGetNewsSuccessful() {
            newsModel = testModelsGenerator.generateNewsModel("Stup")
            val serviceResponse = ServiceResponse(code = ServiceError.SUCCESS_CODE, data = newsModel)
            coEvery { dataRepository!!.requestNews() } returns serviceResponse
            newsUseCase.getNews(callback!!)
            coVerify(exactly = 1, verifyBlock = { callback!!.onSuccess(any()) })
            coVerify(exactly = 0, verifyBlock = { callback!!.onFail() })
    }

    @Test
    fun testGetNewsFail() {
            val serviceResponse = ServiceResponse(code = ServiceError.ERROR_CODE, data = null)
            coEvery { dataRepository!!.requestNews() } returns serviceResponse
            newsUseCase.getNews(callback!!)
            coVerify(exactly = 0, verifyBlock = { callback!!.onSuccess(any()) })
            coVerify(exactly = 1, verifyBlock = { callback!!.onFail() })
    }

    @Test
    fun searchByTitleSuccess() {
        val stup = "this is news Title"
        val newsItem = newsUseCase.searchByTitle(testModelsGenerator.generateNewsModel(stup).newsItems!!, stup)
        assertNotNull(newsItem)
        assertEquals(newsItem!!.title, stup)
    }

    @Test
    fun searchByTitleFail() {
        val stupTitle = "this is news Title"
        val stupSearch = "search title"
        val newsItem = newsUseCase.searchByTitle(testModelsGenerator.generateNewsModel(stupTitle).newsItems!!, stupSearch)
        assertEquals(newsItem, null)
    }

    @AfterEach
    fun tearDown() {
    }
}
