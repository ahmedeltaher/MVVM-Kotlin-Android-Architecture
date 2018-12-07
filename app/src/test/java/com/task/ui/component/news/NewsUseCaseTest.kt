package com.task.ui.component.news

import com.task.data.DataRepository
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.listeners.BaseCallback
import com.task.usecase.NewsUseCase
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
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
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.IOException

/**
 * Created by ahmedeltaher on 3/8/17.
 */

@ExtendWith(MockKExtension::class)
class NewsUseCaseTest {

    private var dataRepository: DataRepository? = null
    private var callback:BaseCallback?=spyk<BaseCallback>()

    private lateinit var newsUseCase: NewsUseCase
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var newsModelTestSubscriber: TestSubscriber<NewsModel>
    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()
    private lateinit var newsModel: NewsModel

    @BeforeEach
    fun setUp() {
        val callable = Schedulers.trampoline()
        RxJavaPlugins.setIoSchedulerHandler { callable }
        RxJavaPlugins.setComputationSchedulerHandler { callable }
        RxJavaPlugins.setNewThreadSchedulerHandler { callable }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { callable }
        RxAndroidPlugins.setMainThreadSchedulerHandler { callable }

        newsModelTestSubscriber = TestSubscriber()
        compositeDisposable = CompositeDisposable()
        dataRepository= DataRepository(mockk(), mockk())
        newsUseCase = NewsUseCase(dataRepository!!,compositeDisposable)
    }

    @Test
    fun testGetNewsSuccessful() {
        newsModel = testModelsGenerator.generateNewsModel("Stup")
        val newsModelSingle: Single<NewsModel>  = Single.create { emitter ->emitter.onSuccess(newsModel) }
        every { dataRepository!!.requestNews()} returns newsModelSingle
        newsUseCase.getNews(callback!!)
        verify(exactly = 1,verifyBlock = { callback!!.onSuccess(any())})
        verify(exactly = 0,verifyBlock = { callback!!.onFail()})
    }

    @Test
    fun testGetNewsFail() {
        val ioEx = IOException()
        val newsModelSingle: Single<NewsModel>  = Single.create { emitter ->emitter.onError(ioEx) }
        every { dataRepository!!.requestNews() }returns newsModelSingle
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
