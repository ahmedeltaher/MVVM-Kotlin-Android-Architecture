package com.task.di

import com.task.TestDataRepository
import com.task.data.DataRepositorySource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class TestDataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: TestDataRepository): DataRepositorySource
}
