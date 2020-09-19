package com.task.di

import com.task.ui.component.details.DetailsViewModel
import com.task.ui.component.login.LoginViewModel
import com.task.ui.component.recipes.RecipesListViewModel
import com.task.ui.component.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {

    viewModel { RecipesListViewModel(dataRepositoryRepository = get(), errorMapper = get()) }
    viewModel { SplashViewModel(errorMapper = get()) }
    viewModel { DetailsViewModel(dataRepository = get(), errorMapper = get()) }
    viewModel { LoginViewModel(dataRepository = get(), errorMapper = get()) }

}
