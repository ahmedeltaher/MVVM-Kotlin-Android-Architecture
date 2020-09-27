package com.task.di

import android.app.Application
import com.task.AppTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [TestDataModule::class,
            AndroidInjectionModule::class,
            AppModule::class,
            ActivityModuleBuilder::class
            , ViewModelModule::class])
interface TestAppComponent : AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(app: AppTest)
}
