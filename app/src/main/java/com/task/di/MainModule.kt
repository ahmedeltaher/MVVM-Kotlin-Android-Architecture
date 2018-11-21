package com.task.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.task.data.local.LocalRepository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by AhmedEltaher on 5/12/2016
 */

@Module
class MainModule {
    @Provides
    @Singleton
    fun provideLocalRepository(): LocalRepository {
        return LocalRepository()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideCompositeSubscription(): CompositeDisposable {
        return CompositeDisposable()
    }
}
