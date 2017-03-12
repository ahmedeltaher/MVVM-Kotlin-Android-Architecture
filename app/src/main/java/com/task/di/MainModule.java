package com.task.di;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.task.data.local.LocalRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

@Module
public class MainModule {
    @Provides
    @Singleton
    public LocalRepository provideLocalRepository() {
        return new LocalRepository();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        Gson gson = new GsonBuilder().create();
        return gson;
    }

    @Provides
    @Singleton
    public CompositeDisposable provideCompositeSubscription() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        return compositeDisposable;
    }
}
