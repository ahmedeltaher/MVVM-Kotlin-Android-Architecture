package com.task.usecase

import androidx.lifecycle.MutableLiveData
import com.task.data.Resource
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel

/**
 * Created by ahmedeltaher on 3/22/17.
 */

interface UseCase {
    fun getNews()
    fun searchByTitle(keyWord: String): NewsItem?
    val newsLiveData: MutableLiveData<Resource<NewsModel>>
}
