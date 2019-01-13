package com.task.ui.component.news

import androidx.lifecycle.ViewModel
import com.task.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class HomeActivityModules {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindUserViewModel(userViewModel: HomeViewModel): ViewModel
}