package com.task.ui.component.splash;

import android.os.Bundle;

import com.task.ui.base.Presenter;

import javax.inject.Inject;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class SplashPresenter extends Presenter<SplashContract.View> implements SplashContract.Presenter {

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getView().NavigateToMainScreen();
    }
}
