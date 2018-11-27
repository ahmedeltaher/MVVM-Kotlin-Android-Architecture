package com.task

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
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
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        fun getDrawableById(resId: Int): Drawable {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                context.resources.getDrawable(resId,context.theme)
            else
                context.resources.getDrawable(resId)
        }
    }

}
