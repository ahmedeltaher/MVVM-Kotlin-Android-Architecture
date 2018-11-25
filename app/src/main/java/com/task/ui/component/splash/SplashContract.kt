package com.task.ui.component.splash

import com.task.ui.base.listeners.BaseView

/**
 * Created by AhmedEltaher on 5/12/2016
 */

interface SplashContract {
    interface View : BaseView {
        fun NavigateToMainScreen()
    }

    interface Presenter
}
