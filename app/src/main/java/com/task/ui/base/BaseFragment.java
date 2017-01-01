package com.task.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.task.R;

import butterknife.ButterKnife;

/**
 * Created by AhmedEltaher on 5/12/2016
 */


public abstract class BaseFragment extends Fragment implements Presenter.View {

    protected FragmentManager fragmentManager;

    protected Presenter presenter;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();

    public abstract int getLayoutId();

    private View view;


    private String toolbarTitleKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        initializeDagger();
        initializePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        if (presenter != null) {
            presenter.initialize(getArguments());
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.finalizeView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setTitle(String title) {
        final ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            TextView titleTextView = ButterKnife.findById(getActivity(), R.id.txt_toolbar_title);
            if (TextUtils.isEmpty(title)) {
                titleTextView.setText(title);
            }
        }
    }
}
