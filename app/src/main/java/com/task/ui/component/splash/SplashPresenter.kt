package com.task.ui.component.splash

import android.os.Bundle

import com.task.ui.base.Presenter

import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class SplashPresenter @Inject
constructor() : Presenter<SplashContract.View>(), SplashContract.Presenter {

    override fun initialize(extras: Bundle?) {
        super.initialize(extras)
        getView()?.NavigateToMainScreen()
    }
}
