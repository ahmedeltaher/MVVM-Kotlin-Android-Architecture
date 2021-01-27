package com.task.di

import com.task.TestDataRepository
import com.task.data.DataRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TestDataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: TestDataRepository): DataRepositorySource
}
