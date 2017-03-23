package com.task.usecase;

import com.task.data.DataRepository;
import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.listeners.BaseCallback;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class NewsUseCase implements UseCase {
    private DataRepository dataRepository;
    private CompositeDisposable mSubscriptions;

    @Inject
    public NewsUseCase(DataRepository dataRepository, CompositeDisposable mSubscriptions) {
        this.dataRepository = dataRepository;
        this.mSubscriptions = mSubscriptions;
    }

    @Override
    public void getNews(final BaseCallback callback) {
        mSubscriptions.add(dataRepository.requestNews().observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsModel -> callback.onSuccess(newsModel),
                        exception -> callback.onFail()));
    }

    @Override
    public NewsItem searchByTitle(List<NewsItem> news, String keyWord) {
        for (NewsItem newsItem : news) {
            if (newsItem.getTitle().toLowerCase().contains(keyWord.toLowerCase())) {
                return newsItem;
            }
        }
        return null;
    }

    public void unSubscribe() {
        mSubscriptions.clear();
    }
}
