package com.task.ui.component.splash

import android.os.Handler
import com.task.App
import com.task.R
import com.task.ui.base.BaseActivity
import com.task.ui.component.news.HomeActivity
import com.task.utils.Constants
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class SplashActivity : BaseActivity(), SplashContract.View {

    @Inject
    lateinit var splashPresenter: SplashPresenter

    override val layoutId: Int
        get() = R.layout.splash_layout

    override fun initializeDagger() {
        val app = applicationContext as App
        app.mainComponent?.inject(this@SplashActivity)
    }

    override fun initializePresenter() {
        splashPresenter.setView(this)
        super.presenter = splashPresenter
    }

    override fun NavigateToMainScreen() {
        Handler().postDelayed({
            startActivity<HomeActivity>()
            finish()
        }, Constants.SPLASH_DELAY.toLong())
    }
}
