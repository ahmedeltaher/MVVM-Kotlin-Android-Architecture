package com.task.di

import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.login.LoginActivity
import com.task.ui.component.recipes.RecipesListActivity
import com.task.ui.component.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeHomeActivity(): RecipesListActivity

    @ContributesAndroidInjector()
    abstract fun contributeDetailsActivity(): DetailsActivity

    @ContributesAndroidInjector()
    abstract fun contributeLoginActivity(): LoginActivity
}
