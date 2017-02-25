package com.task.di;


import com.task.ui.component.details.DetailsActivity;
import com.task.ui.component.news.HomeActivity;
import com.task.ui.component.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AhmedEltaher on 5/12/2016
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(SplashActivity activity);

    void inject(HomeActivity activity);

    void inject(DetailsActivity activity);
}
