package com.task.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.task.databinding.SplashLayoutBinding
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.news.NewsListActivity
import com.task.utils.Constants
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

class SplashActivity : BaseActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var splashViewModel: SplashViewModel

    private lateinit var binding: SplashLayoutBinding

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun initializeViewModel() {
        splashViewModel = viewModelFactory.create(splashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun observeViewModel() {

    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            val nextScreenIntent = Intent(this, NewsListActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, Constants.SPLASH_DELAY.toLong())
    }
}