package com.task.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.DataSource
import com.task.data.DataStatus
import com.task.data.remote.Error
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
    var error: LiveData<Error> = MutableLiveData<Error>()

    private val newsMutableLiveData = MutableLiveData<NewsModel>()
    override val newsLiveData: LiveData<NewsModel> = newsMutableLiveData

    override fun getNews(callback: BaseCallback) {
        launch {
            try {
                val serviceResponse: DataStatus = withContext(Dispatchers.IO) { dataRepository.requestNews() }
                when (serviceResponse) {
                    is DataStatus.LoadingState -> {
                    }
                    is DataStatus.SuccessState -> {
                        callback.onSuccess(serviceResponse.data.data as NewsModel)
//                        newsMutableLiveData.postValue(serviceResponse.data.data as NewsModel)
                    }
                    is DataStatus.FailureState -> {
                        callback.onFail(serviceResponse.error)
                    }
                }

//                if (serviceResponse?.code == Error.SUCCESS_CODE) {
//                    val data = serviceResponse.data
//                    callback.onSuccess(data as NewsModel)
//                } else {
//                    callback.onFail(serviceResponse?.error ?: Error(code = INTERNAL_SERVER_ERROR))
//                }
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
