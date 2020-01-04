package com.task.usecase

import androidx.lifecycle.MutableLiveData
import com.task.data.DataSource
import com.task.data.Resource
import com.task.data.error.Error
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsUseCase @Inject
constructor(private val dataRepository: DataSource, override val coroutineContext: CoroutineContext) : UseCase, CoroutineScope {
    private val newsMutableLiveData = MutableLiveData<Resource<NewsModel>>()
    override val newsLiveData: MutableLiveData<Resource<NewsModel>> = newsMutableLiveData


    override fun getNews() {
        var serviceResponse: Resource<NewsModel>?
        newsMutableLiveData.postValue(Resource.Loading())
        println("this is the println before lunch")
        launch {
            try {
                serviceResponse = dataRepository.requestNews()
                println("{serviceResponse is here ${serviceResponse?.data}")
                newsMutableLiveData.postValue(serviceResponse)
                println("{newsMutableLiveData is here ${newsMutableLiveData.value?.data}")
            } catch (e: Exception) {
                println("{Exception is here $e")
                newsMutableLiveData.postValue(Resource.DataError(Error(e)))
            }
            println("this is the println inside lunch")
        }
        println("this is the println at end of lunch")
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
