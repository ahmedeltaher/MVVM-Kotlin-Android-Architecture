package com.task.ui.component.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.task.App;
import com.task.R;
import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.BaseActivity;
import com.task.ui.component.details.DetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.task.utils.Constants.NEWS_ITEM_KEY;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class HomeActivity extends BaseActivity implements HomeView {
    @Inject
    HomePresenter presenter;
    @Bind(R.id.rv_news_list)
    RecyclerView rvNews;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_no_data)
    TextView tvNoData;
    @Bind(R.id.rl_news_list)
    RelativeLayout rlNewsList;
    @Bind(R.id.btn_search)
    ImageButton btnSearch;
    @Bind(R.id.et_search)
    EditText editTextSearch;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(HomeActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    public void initializeNewsList(List<NewsItem> news) {
        NewsAdapter newsAdapter = new NewsAdapter(presenter.getRecyclerItemListener(), news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNews.setLayoutManager(layoutManager);
        rvNews.setHasFixedSize(true);
        rvNews.setAdapter(newsAdapter);
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void navigateToDetailsScreen(NewsItem news) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(NEWS_ITEM_KEY, news);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void setNoDataVisibility(boolean isVisible) {
        tvNoData.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void setListVisibility(boolean isVisible) {
        rlNewsList.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void showSearchError() {
        Snackbar.make(rlNewsList, getString(R.string.search_error), LENGTH_SHORT).show();
    }

    @Override
    public void showMenuMapError() {
        Snackbar.make(rlNewsList, getString(R.string.news_error), LENGTH_SHORT).show();
    }

    @OnClick({R.id.ic_toolbar_setting, R.id.ic_toolbar_refresh, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_toolbar_refresh:
                presenter.getNews();
                break;
            case R.id.btn_search:
                presenter.onSearchClick(editTextSearch.getText().toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }
}
