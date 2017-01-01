package com.task.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.task.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

@SuppressWarnings("ConstantConditions")
public abstract class BaseActivity extends AppCompatActivity implements Presenter.View, ActionBarView {

    protected Presenter presenter;

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Bind(R.id.ic_toolbar_setting)
    ImageView icSettings;

    @Nullable
    @Bind(R.id.ic_toolbar_refresh)

    protected
    ImageView icHome;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initializeButterKnife();
        initializeDagger();
        initializePresenter();
        initializeToolbar();

        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.finalizeView();
        }
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    protected void initializeToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public void setUpIconVisibility(boolean visible) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(visible);
        }
    }

    @Override
    public void setTitle(String titleKey) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            TextView title = ButterKnife.findById(this, R.id.txt_toolbar_title);
            if (title != null) {
                title.setText(titleKey);
            }
        }
    }

    @Override
    public void setSettingsIconVisibility(boolean visible) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ImageView icon = ButterKnife.findById(this, R.id.ic_toolbar_setting);
            if (icon != null) {
                icon.setVisibility(visible ? VISIBLE : GONE);
            }
        }
    }

    @Override
    public void setRefreshVisibility(boolean visible) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ImageView icon = ButterKnife.findById(this, R.id.ic_toolbar_refresh);
            if (icon != null) {
                icon.setVisibility(visible ? VISIBLE : GONE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
