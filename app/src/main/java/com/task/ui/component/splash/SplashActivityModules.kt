package com.task.ui.component.splash

import androidx.lifecycle.ViewModel
import com.task.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class SplashActivityModules {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindUserViewModel(userViewModel: SplashViewModel): ViewModel
}