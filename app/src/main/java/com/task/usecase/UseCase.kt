package com.task.usecase

import androidx.lifecycle.LiveData
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.listeners.BaseCallback

/**
 * Created by ahmedeltaher on 3/22/17.
 */

interface UseCase {
    fun getNews(callback: BaseCallback)

    fun searchByTitle(news: List<NewsItem>, keyWord: String): NewsItem?
    val newsLiveData: LiveData<NewsModel>
}
