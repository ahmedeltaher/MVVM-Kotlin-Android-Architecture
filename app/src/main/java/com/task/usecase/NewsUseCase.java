package com.task.usecase;

import android.support.annotation.NonNull;

import com.task.data.DataRepository;
import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class NewsUseCase {
    DataRepository dataRepository;
    @NonNull
    private CompositeSubscription mSubscriptions;

    @Inject
    public NewsUseCase(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.mSubscriptions = new CompositeSubscription();
    }

    public void getNews(final Callback callback) {
        mSubscriptions.add(dataRepository.requestNews().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(newsModel -> callback.onSuccess(newsModel),
                exception -> {
                    callback.onFail();
                }));
    }

    public NewsItem searchByTitle(List<NewsItem> news, String keyWord) {
        for (NewsItem newsItem : news) {
            if (newsItem.getTitle().toLowerCase().contains(keyWord.toLowerCase())) {
                return newsItem;
            }
        }
        return null;
    }

    public void unSubscribe() {
        mSubscriptions.unsubscribe();
    }

    public interface Callback {
        void onSuccess(NewsModel newsModel);

        void onFail();
    }
}
