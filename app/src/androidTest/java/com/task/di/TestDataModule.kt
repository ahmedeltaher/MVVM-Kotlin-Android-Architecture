package com.task.di

import com.task.TestDataReprository
import com.task.data.DataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class TestDataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: TestDataReprository): DataSource
}