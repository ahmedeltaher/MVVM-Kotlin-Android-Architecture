package com.task

import androidx.multidex.MultiDexApplication
import com.task.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by AhmedEltaher
 */

open class App : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    open fun initKoin() {
        startKoin {
            androidLogger()
            // this provides app context anywhere using get()
            androidContext(this@App.applicationContext)
            modules(
                    listOf(
                            AppModule,
                            NetworkModule,
                            RepositoriesModule,
                            ViewModelModule,
                            ErrorModule
                    )
            )
        }
    }

}
