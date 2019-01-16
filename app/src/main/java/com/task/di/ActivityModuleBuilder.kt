/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.task.di
import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.news.NewsListActivity
import com.task.ui.component.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): NewsListActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailsActivity(): DetailsActivity
}
