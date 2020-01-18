package com.task.ui.component.news

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.Resource
import com.task.data.error.mapper.ErrorMapper
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.BaseViewModel
import com.task.usecase.NewsUseCase
import com.task.usecase.errors.ErrorManager
import com.task.utils.Event
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsListViewModel @Inject
constructor(private val newsDataUseCase: NewsUseCase) : BaseViewModel() {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */
    var newsLiveData: MutableLiveData<Resource<NewsModel>> = newsDataUseCase.newsLiveData

    private val newsSearchFoundPrivate: MutableLiveData<NewsItem> = MutableLiveData()
    val newsSearchFound: LiveData<NewsItem> get() = newsSearchFoundPrivate

    private val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate

    /**
     * UI actions as event, user action is single one time event, Shouldn't be multiple time consumption
     */
    private val openNewsDetailsPrivate = MutableLiveData<Event<NewsItem>>()
    val openNewsDetails: LiveData<Event<NewsItem>> get() = openNewsDetailsPrivate

    /**
     * Error handling as UI
     */
    private val showSnackBarPrivate = MutableLiveData<Event<Int>>()
    val showSnackBar: LiveData<Event<Int>> get() = showSnackBarPrivate

    private val showToastPrivate = MutableLiveData<Event<Any>>()
    val showToast: LiveData<Event<Any>> get() = showToastPrivate


    fun getNews() {
        newsDataUseCase.getNews()
    }

    fun openNewsDetails(newsItem: NewsItem) {
        openNewsDetailsPrivate.value = Event(newsItem)
    }

    fun showSnackbarMessage(@StringRes message: Int) {
        showSnackBarPrivate.value = Event(message)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = Event(error.description)
    }

    fun onSearchClick(newsTitle: String) {
        if (newsTitle.isNotEmpty()) {
            val newsItem = newsDataUseCase.searchByTitle(newsTitle)
            if (newsItem != null) {
                newsSearchFoundPrivate.value = newsItem
            } else {
                noSearchFoundPrivate.postValue(Unit)
            }
        } else {
            noSearchFoundPrivate.postValue(Unit)
        }
    }
}
