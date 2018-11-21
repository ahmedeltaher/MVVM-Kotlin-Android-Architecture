package com.task.usecase

import com.task.data.DataRepository
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import com.task.ui.base.listeners.BaseCallback
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsUseCase @Inject
constructor(private val dataRepository: DataRepository, private val compositeDisposable: CompositeDisposable) : UseCase {
    private var newsDisposable: Disposable? = null
    private lateinit var newsModelSingle: Single<NewsModel>
    private var disposableSingleObserver: DisposableSingleObserver<NewsModel>? = null

    override fun getNews(callback: BaseCallback) {
        disposableSingleObserver = object : DisposableSingleObserver<NewsModel>() {
            override fun onSuccess(newsModel: NewsModel) {
                callback.onSuccess(newsModel)
            }

            override fun onError(e: Throwable) {
                callback.onFail()
            }
        }
        if (!compositeDisposable.isDisposed) {
            newsModelSingle = dataRepository.requestNews()
            newsDisposable = newsModelSingle.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(disposableSingleObserver!!)
            compositeDisposable.add(newsDisposable!!)
        }
    }

    override fun searchByTitle(news: List<NewsItem>, keyWord: String): NewsItem? {
        for (newsItem in news) {
            if (newsItem.title!!.toLowerCase().contains(keyWord.toLowerCase())) {
                return newsItem
            }
        }
        return null
    }

    fun unSubscribe() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.remove(newsDisposable!!)
        }
    }
}
