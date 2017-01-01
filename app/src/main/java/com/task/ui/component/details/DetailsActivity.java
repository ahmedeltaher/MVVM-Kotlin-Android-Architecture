package com.task.ui.component.details;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.task.App;
import com.task.R;
import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.Bind;

import static android.text.TextUtils.isEmpty;

/**
 * Created by AhmedEltaher on 11/12/16.
 */

public class DetailsActivity extends BaseActivity implements DetailsView {

    @Inject
    DetailsPresenter presenter;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_news_main_Image)
    ImageView ivMainImage;
    @Bind(R.id.tv_description)
    TextView tvDescription;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(DetailsActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.details_layout;
    }

    @Override
    public void initializeView(NewsItem newsItem) {
        if (!isEmpty(newsItem.getAbstract())) {
            tvDescription.setText(newsItem.getAbstract());
        }
        if (!isEmpty(newsItem.getTitle())) {
            tvTitle.setText(newsItem.getTitle());
        }
        Picasso picasso = Picasso.with(this);
        RequestCreator requestCreator;
        if (!isEmpty(presenter.getMainImageURL())) {
            requestCreator = picasso.load(presenter.getMainImageURL());
        } else {
            requestCreator = picasso.load(R.drawable.news);
        }
        requestCreator.placeholder(R.drawable.news).into(ivMainImage);
    }
}
