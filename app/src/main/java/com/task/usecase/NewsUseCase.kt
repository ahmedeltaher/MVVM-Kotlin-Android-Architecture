package com.task.usecase

import com.task.data.DataRepository
import com.task.data.remote.Data
import com.task.data.remote.Error
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.listeners.BaseCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.observers.DisposableSingleObserver as DisposableSingleObserver1


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsUseCase @Inject
constructor(private val dataRepository: DataRepository) : UseCase {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun getNews(callback: BaseCallback) {
        val disposableSingleObserver = object : DisposableSingleObserver1<Data>() {

            override fun onSuccess(data: Data) {
                callback.onSuccess(data.data as NewsModel)
            }

            override fun onError(throwable: Throwable) {
                callback.onFail(throwable as Error)
            }
        }
        if (!compositeDisposable?.isDisposed) {
            val newsModelSingle = dataRepository.requestNews()
            val newsDisposable = newsModelSingle.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith<DisposableSingleObserver1<Data>>(disposableSingleObserver)
            compositeDisposable.add(newsDisposable)
        }
    }

    override fun searchByTitle(news: List<NewsItem>, keyWord: String): NewsItem? {
        for (newsItem in news) {
            if (!newsItem.title.isNullOrEmpty() && newsItem.title!!.toLowerCase().contains(keyWord.toLowerCase())) {
                return newsItem
            }
        }
        return null
    }

    fun unSubscribe() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
