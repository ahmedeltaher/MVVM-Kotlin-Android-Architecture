package com.task.ui.component.news;

import com.task.data.DataRepository;
import com.task.data.remote.dto.NewsItem;
import com.task.data.remote.dto.NewsModel;
import com.task.ui.base.listeners.BaseCallback;
import com.task.usecase.NewsUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ahmedeltaher on 3/8/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class NewsUseCaseTest {

    @Mock
    DataRepository dataRepository;
    @Mock
    BaseCallback callback;


    NewsUseCase newsUseCase;
    CompositeDisposable compositeDisposable;
    Observable<NewsModel> newsModelObservable;
    TestSubscriber<NewsModel> newsModelTestSubscriber;
    TestModelsGenerator testModelsGenerator;
    NewsModel newsModel;

    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__ -> Schedulers.trampoline());
        testModelsGenerator = new TestModelsGenerator();
        newsModelTestSubscriber = new TestSubscriber<>();
        compositeDisposable = new CompositeDisposable();
    }

    @Test
    public void testGetNewsSuccessful() {
        newsModel = (NewsModel) testModelsGenerator.getNewsSuccessfulModel().getData();
        newsModelObservable = Observable.just(newsModel);

        when(dataRepository.requestNews()).thenReturn(newsModelObservable);

        newsUseCase = new NewsUseCase(dataRepository, compositeDisposable);
        newsUseCase.getNews(callback);

        verify(callback, times(1)).onSuccess(any(NewsModel.class));
        verify(callback, never()).onFail();
    }

    @Test
    public void testGetNewsFail() {
        when(dataRepository.requestNews()).thenReturn(Observable.empty());

        newsUseCase = new NewsUseCase(dataRepository, compositeDisposable);
        newsUseCase.getNews(callback);

        verify(callback, never()).onSuccess(any(NewsModel.class));
    }

    @Test
    public void searchByTitleSuccess() {
        String stup = "this is news Title";
        newsUseCase = new NewsUseCase(dataRepository, compositeDisposable);
        NewsItem newsItem = newsUseCase.searchByTitle(testModelsGenerator.generateNewsModel(stup).getNewsItems(), stup);
        assertNotNull(newsItem);
        assertEquals(newsItem.getTitle(), stup);
    }

    @Test
    public void searchByTitleFail() {
        String stupTitle = "this is news Title";
        String stupSearch = "search title";
        newsUseCase = new NewsUseCase(dataRepository, compositeDisposable);
        NewsItem newsItem = newsUseCase.searchByTitle(testModelsGenerator.generateNewsModel(stupTitle).getNewsItems(), stupSearch);
        assertEquals(newsItem, null);
    }
}
