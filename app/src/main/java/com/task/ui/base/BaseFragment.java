package com.task.ui.base;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.task.R;
import com.task.ui.base.listeners.BaseView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by AhmedEltaher on 5/12/2016
 */


public abstract class BaseFragment extends Fragment implements BaseView {

    protected FragmentManager fragmentManager;

    protected Presenter presenter;

    protected abstract void initializeDagger ();

    protected abstract void initializePresenter ();

    public abstract int getLayoutId ();

    private View view;

    private Unbinder unbinder;

    private String toolbarTitleKey;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        initializeDagger();
        initializePresenter();
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        if (presenter != null) {
            presenter.initialize(getArguments());
        }
        return view;
    }

    @Override
    public void onStart () {
        super.onStart();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    public void onStop () {
        super.onStop();
        if (presenter != null) {
            presenter.finalizeView();
        }
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setTitle (String title) {
        final ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            TextView titleTextView = getActivity().findViewById(R.id.txt_toolbar_title);
            if (TextUtils.isEmpty(title)) {
                titleTextView.setText(title);
            }
        }
    }
}
