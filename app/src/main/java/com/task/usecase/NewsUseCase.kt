package com.task.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.DataSource
import com.task.data.Resource
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

    private val newsMutableLiveData = MutableLiveData<Resource<NewsModel>>()
    override val newsLiveData: LiveData<Resource<NewsModel>> = newsMutableLiveData


    override fun getNews() {
        newsMutableLiveData.postValue(Resource.Loading())
        var serviceResponse: Resource<NewsModel>?
        launch {
            withContext(Dispatchers.IO) {
                try {
                    serviceResponse = dataRepository.requestNews()
                    println("{serviceResponse is here ${serviceResponse?.data}")
                    newsMutableLiveData.postValue(serviceResponse)
                    println("{newsMutableLiveData is here ${newsMutableLiveData.value?.data}")
                } catch (e: Exception) {
                    println("{Exception is here $e")
                    newsMutableLiveData.postValue(Resource.DataError(Error(e)))
                }
            }
        }
    }

    override fun searchByTitle(keyWord: String): NewsItem? {
        val news = newsMutableLiveData.value?.data?.newsItems
        if (!news.isNullOrEmpty()) {
            for (newsItem in news) {
                if (newsItem.title.isNotEmpty() && newsItem.title.toLowerCase().contains(keyWord.toLowerCase())) {
                    return newsItem
                }
            }
        }
        return null
    }
}
