package com.task.di


import com.task.data.local.LocalData
import com.task.data.remote.RemoteData
import com.task.utils.Network
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext


val AppModule = module {

    single { LocalData(context = get()) }

    single { }

    single { getBackgroundThread() }

    single { Network(context = get()) }

    single {
        RemoteData(
                serviceGenerator = get(),
                networkConnectivity = get()
        )
    }

}

fun getBackgroundThread(): CoroutineContext {
    return Dispatchers.IO
}