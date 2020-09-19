package com.task.di

import com.task.data.error.mapper.ErrorMapper
import com.task.data.error.mapper.ErrorMapperInterface
import com.task.usecase.errors.ErrorFactory
import com.task.usecase.errors.ErrorManager
import org.koin.dsl.module


val ErrorModule = module {

    single {
        ErrorManager(get())
    }

    single {
        ErrorMapper(get())
    }
}
