package com.task.ui.component.news

import com.task.data.remote.dto.NewsModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.NewsUseCase
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class HomePresenterTest {
    private val newsUseCase: NewsUseCase = mockk()
    private val newsModelMock: NewsModel = mockk()
    private val homeContract: HomeContract.View = spyk()
    private val callback: BaseCallback = spyk()

    private var homePresenter: HomePresenter? = null
    private val newsTitle = "this is test"
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @BeforeEach
    fun setUp() {
        clearMocks(newsModelMock)
        clearMocks(callback)
        homePresenter = HomePresenter(newsUseCase)
        homePresenter!!.setView(homeContract)
    }

    @Test
    fun getNewsList() {
        // Let's do a synchronous answer for the callback
        val newsModel = testModelsGenerator.generateNewsModel(newsTitle)
        //1- Mock
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { newsUseCase.getNews(callback = capture(callbackCapture!!)) } answers
                { callbackCapture.captured.onSuccess(newsModel!!) }
        //2-Call
        homePresenter!!.getNews()
        //3-verify
        assert(newsModel == homePresenter!!.newsModel!!)
        verify(exactly = 1, verifyBlock = { homeContract!!.setLoaderVisibility(true) })
        verify(exactly = 2, verifyBlock = { homeContract!!.setNoDataVisibility(false) })
        verify(exactly = 1, verifyBlock = { homeContract!!.setListVisibility(false) })
        verify(exactly = 1, verifyBlock = { homeContract!!.setListVisibility(false) })
        verify(exactly = 1, verifyBlock = { newsUseCase!!.getNews(callbackCapture!!.captured) })
    }

    @Test
    fun testSearchSuccess() {
        val newsItem = testModelsGenerator.generateNewsItemModel(newsTitle)
        val newsModel = testModelsGenerator.generateNewsModel(newsTitle)
        //1- Mock
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { newsUseCase.getNews(callback = capture(callbackCapture!!)) } answers
                { callbackCapture.captured.onSuccess(newsModel!!) }
        every { newsUseCase.searchByTitle(newsModel!!.newsItems!!, newsTitle) } returns newsItem
        //2- Call
        homePresenter!!.getNews()
        homePresenter!!.onSearchClick(newsTitle)
        //3- Verify
        assert(newsModel == homePresenter!!.newsModel!!)
        verify(exactly = 1, verifyBlock = { homeContract!!.navigateToDetailsScreen(any()) })
    }

    @Test
    fun testSearchFailedWhileEmptyList() {
        //1- Mock
        val newsModelWithEmptyList: NewsModel = testModelsGenerator.generateNewsModelWithEmptyList("stup")
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { newsUseCase.getNews(callback = capture(callbackCapture!!)) } answers
                { callbackCapture.captured.onSuccess(newsModelWithEmptyList) }
        every { newsUseCase.searchByTitle(newsModelWithEmptyList!!.newsItems!!, newsTitle) } returns null
        //2- Call
        homePresenter!!.getNews()
        homePresenter!!.onSearchClick(newsTitle)
        //3- Verify
        assert(0 == homePresenter?.newsModel?.newsItems?.size)
        verify(exactly = 1, verifyBlock = { homeContract!!.showSearchError() })
    }

    @Test
    fun testSearchFailedWhenNothingMatches() {
        val newsModel = testModelsGenerator.generateNewsModel(newsTitle)
        //1- Mock
        val callbackCapture: CapturingSlot<BaseCallback> = slot()
        every { newsUseCase.getNews(callback = capture(callbackCapture!!)) } answers
                { callbackCapture.captured.onSuccess(newsModel!!) }
        every { newsUseCase.searchByTitle(newsModel!!.newsItems!!, newsTitle) } returns null
        //2- Call
        homePresenter!!.getNews()
        homePresenter!!.onSearchClick(newsTitle)
        //3- Verify
        assert(homePresenter?.newsModel?.newsItems?.isNullOrEmpty() == false)
        verify(exactly = 1, verifyBlock = { homeContract!!.showSearchError() })
    }

    @AfterEach
    fun tearDown() {

    }
}
