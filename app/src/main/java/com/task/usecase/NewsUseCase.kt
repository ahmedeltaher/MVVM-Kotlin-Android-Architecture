package com.task.usecase

import com.task.data.DataSource
import com.task.data.remote.Data
import com.task.data.remote.Error
import com.task.data.remote.Error.Companion.INTERNAL_SERVER_ERROR
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.listeners.BaseCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsUseCase @Inject
constructor(private val dataRepository: DataSource, override val coroutineContext: CoroutineContext) : UseCase, CoroutineScope {

    override fun getNews(callback: BaseCallback) {
        launch {
            try {
                val serviceResponse: Data? = withContext(Dispatchers.IO) { dataRepository.requestNews() }
                if (serviceResponse?.code == Error.SUCCESS_CODE) {
                    val data = serviceResponse.data
                    callback.onSuccess(data as NewsModel)
                } else {
                    callback.onFail(serviceResponse?.error ?: Error(code = INTERNAL_SERVER_ERROR))
                }
            } catch (e: Exception) {
                callback.onFail(Error(e))
            }
        }
    }

    override fun searchByTitle(news: List<NewsItem>, keyWord: String): NewsItem? {
        for (newsItem in news) {
            if (newsItem.title.isNotEmpty() && newsItem.title.toLowerCase().contains(keyWord.toLowerCase())) {
                return newsItem
            }
        }
        return null
    }
}
