package com.task.di


import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.news.HomeActivity
import com.task.ui.component.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by AhmedEltaher on 5/12/2016
 */
@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: SplashActivity)

    fun inject(activity: HomeActivity)

    fun inject(activity: DetailsActivity)
}
