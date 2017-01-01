package com.task.ui.component.home;

import android.os.Bundle;

import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;
import com.task.ui.base.Presenter;
import com.task.ui.base.listeners.RecyclerItemListener;
import com.task.usecase.NewsUseCase;
import com.task.usecase.NewsUseCase.Callback;

import java.util.List;

import javax.inject.Inject;

import static android.text.TextUtils.isEmpty;
import static com.task.utils.ObjectUtil.isNull;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class HomePresenter extends Presenter<HomeView> {

    private final NewsUseCase newsUseCase;
    private NewsModel newsModel;

    @Inject
    public HomePresenter(NewsUseCase newsUseCase) {
        this.newsUseCase = newsUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getNews();
    }

    public void getNews() {
        getView().setLoaderVisibility(true);
        getView().setNoDataVisibility(false);
        getView().setListVisibility(false);
        newsUseCase.getNews(callback);
    }

    public void unSubscribe() {
        newsUseCase.unSubscribe();
    }

    public RecyclerItemListener getRecyclerItemListener() {
        return recyclerItemListener;
    }

    public void onSearchClick(String newsTitle) {
        List<NewsItem> news = newsModel.getNewsItems();
        if (!isEmpty(newsTitle) && !isNull(news) && !news.isEmpty()) {
            NewsItem newsItem = newsUseCase.searchByTitle(news, newsTitle);
            if (!isNull(newsItem)) {
                getView().navigateToDetailsScreen(newsItem);
            } else {
                getView().showSearchError();
            }
        } else {
            getView().showSearchError();
        }
    }

    private void showList(boolean isVisible) {
        getView().setNoDataVisibility(!isVisible);
        getView().setListVisibility(isVisible);
    }

    private final RecyclerItemListener recyclerItemListener = position -> {
        getView().navigateToDetailsScreen(newsModel.getNewsItems().get(position));
    };

    private final Callback callback = new Callback() {
        @Override
        public void onSuccess(NewsModel newsModel) {
            HomePresenter.this.newsModel = newsModel;
            List<NewsItem> newsItems = newsModel.getNewsItems();
            if (!isNull(newsItems) && !newsItems.isEmpty()) {
                getView().initializeNewsList(newsModel.getNewsItems());
                showList(true);
            } else {
                showList(false);
            }
            getView().setLoaderVisibility(false);
        }

        @Override
        public void onFail() {
            showList(false);
            getView().setLoaderVisibility(false);
        }
    };
}
