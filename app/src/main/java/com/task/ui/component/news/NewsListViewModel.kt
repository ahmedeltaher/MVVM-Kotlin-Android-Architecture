package com.task.ui.component.news

import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.data.DataRepositorySource
import com.task.data.Resource
import com.task.data.error.mapper.ErrorMapper
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.BaseViewModel
import com.task.usecase.errors.ErrorManager
import com.task.utils.Event
import com.task.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Locale.ROOT
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

class NewsListViewModel @Inject
constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    /**
     * Data --> LiveData, Exposed as LiveData, Locally in viewModel as MutableLiveData
     */
    @VisibleForTesting
    val _newsLiveData = MutableLiveData<Resource<NewsModel>>()
    val newsLiveData: LiveData<Resource<NewsModel>> get() = _newsLiveData

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
        viewModelScope.launch {
            _newsLiveData.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepositoryRepository.requestNews().collect {
                    _newsLiveData.value = it
                }
            }
        }
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
        _newsLiveData.value?.data?.results?.let {
            if (it.isNotEmpty()) {
                for (newsItem in it) {
                    if (newsItem.title.toLowerCase(ROOT).contains(newsTitle.toLowerCase(ROOT))) {
                        newsSearchFoundPrivate.value = newsItem
                        return
                    }
                }
            }
        }
        return noSearchFoundPrivate.postValue(Unit)
    }
}
