package com.task
import com.task.di.DaggerTestAppComponent

class AppTest: App() {
    override fun initDagger() {
        DaggerTestAppComponent.builder().application(this).build().inject(this)
    }
}
