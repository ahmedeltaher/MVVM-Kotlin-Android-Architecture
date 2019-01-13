package com.task.ui.component.details

import androidx.lifecycle.ViewModel
import com.task.di.ViewModelKey
import com.task.ui.component.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class DetailsActivityModules {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindUserViewModel(userViewModel: SplashViewModel): ViewModel
}
