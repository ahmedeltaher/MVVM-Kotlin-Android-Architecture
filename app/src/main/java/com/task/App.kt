package com.task

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.multidex.MultiDexApplication
import com.task.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class App : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        context = applicationContext
    }

    override fun activityInjector() = dispatchingAndroidInjector

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        fun getDrawableById(resId: Int): Drawable {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                context.resources.getDrawable(resId, context.theme)
            else
                context.resources.getDrawable(resId)
        }
    }
}
