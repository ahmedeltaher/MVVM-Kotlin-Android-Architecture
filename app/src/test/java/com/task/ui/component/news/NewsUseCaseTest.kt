package com.task.ui.component.news

import com.task.data.DataRepository
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.NewsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.IOException

/**
 * Created by ahmedeltaher on 3/8/17.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NewsUseCaseTest {

    private var dataRepository: DataRepository? = null
    private var callback:BaseCallback?=null


    private lateinit var newsUseCase: NewsUseCase
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var newsModelTestSubscriber: TestSubscriber<NewsModel>
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()
    private lateinit var newsModel: NewsModel

    @BeforeAll
    fun setUp() {
        val callable = Schedulers.trampoline()
        RxJavaPlugins.setIoSchedulerHandler { callable }
        RxJavaPlugins.setComputationSchedulerHandler { callable }
        RxJavaPlugins.setNewThreadSchedulerHandler { callable }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { callable }
        RxAndroidPlugins.setMainThreadSchedulerHandler { callable }

        callback = mockk()
        dataRepository = mockk()
        newsModelTestSubscriber = TestSubscriber()
        compositeDisposable = CompositeDisposable()
        newsUseCase = NewsUseCase(dataRepository!!,compositeDisposable)
    }

    @Test
    fun testGetNewsSuccessful() {
        newsModel = testModelsGenerator.newsSuccessfulModel.data as NewsModel
        val newsModelSingle: Single<NewsModel>  = Single.create { emitter ->emitter.onSuccess(newsModel) }
        every { dataRepository!!.requestNews()} answers {newsModelSingle}
        newsUseCase.getNews(callback!!)
        verify(exactly = 1,verifyBlock = { callback!!.onSuccess(any())})
        verify(exactly = 0,verifyBlock = { callback!!.onFail()})
    }

    @Test
    fun testGetNewsFail() {
        val ioEx = IOException()
        every { dataRepository!!.requestNews() }returns ioEx as Single<*>
        newsUseCase.getNews(callback!!)
        verify(exactly = 0,verifyBlock = { callback!!.onSuccess(any())})
        verify(exactly = 1,verifyBlock = { callback!!.onFail()})
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
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}
