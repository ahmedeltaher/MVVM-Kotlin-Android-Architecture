
package com.task.di

import com.task.data.DataRepository
import org.koin.dsl.module

val RepositoriesModule = module {
    single {
        DataRepository(
                ioDispatcher = get(),
                localRepository = get(),
                remoteRepository = get()
        )
    }
}
