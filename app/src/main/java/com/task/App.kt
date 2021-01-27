package com.task

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by AhmedEltaher
 */
@HiltAndroidApp
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}
