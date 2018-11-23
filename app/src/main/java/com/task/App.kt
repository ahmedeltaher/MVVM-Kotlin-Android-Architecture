package com.task

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.multidex.MultiDexApplication

import com.task.di.DaggerMainComponent
import com.task.di.MainComponent

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class App : MultiDexApplication() {
    var mainComponent: MainComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.create()
        context = applicationContext
    }

    @VisibleForTesting
    fun setComponent(mainComponent: MainComponent) {
        this.mainComponent = mainComponent
    }

    companion object {
        lateinit var context: Context
    }
}
