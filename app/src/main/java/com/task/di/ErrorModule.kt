package com.task.di

import com.task.data.error.ErrorFactory
import com.task.data.error.ErrorFactoryImpl
import com.task.data.error.ErrorMapper
import com.task.data.error.ErrorMapperInterface
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

// Tells Dagger this is a Dagger module
@Module
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorFactoryImpl: ErrorFactoryImpl): ErrorFactory

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperInterface
}
