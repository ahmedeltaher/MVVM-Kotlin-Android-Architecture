package com.task

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.task.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

open class App : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initDagger()
    }

    open fun initDagger() {
        DaggerAppComponent.builder().build().inject(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

    companion object {
        lateinit var context: Context

    }
}
