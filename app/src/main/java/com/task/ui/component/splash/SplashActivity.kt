package com.task.ui.component.splash

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.task.R
import com.task.ui.base.BaseActivity
import com.task.ui.component.news.HomeActivity
import com.task.utils.Constants
import dagger.android.DispatchingAndroidInjector
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class SplashActivity : BaseActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override val layoutId: Int
        get() = R.layout.splash_layout

    override fun initializeViewModel() {
        splashViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(splashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            startActivity<HomeActivity>()
            finish()
        }, Constants.SPLASH_DELAY.toLong())
    }
}
