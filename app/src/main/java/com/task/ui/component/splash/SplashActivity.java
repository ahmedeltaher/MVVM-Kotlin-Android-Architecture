package com.task.ui.component.splash;

import android.content.Intent;
import android.os.Handler;

import com.task.App;
import com.task.R;
import com.task.ui.base.BaseActivity;
import com.task.ui.component.news.HomeActivity;

import javax.inject.Inject;

import static com.task.utils.Constants.SPLASH_DELAY;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(SplashActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.splash_layout;
    }


    @Override
    public void NavigateToMainScreen() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
}
