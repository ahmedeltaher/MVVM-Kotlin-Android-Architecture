package com.task.ui.component.details;

import android.os.Bundle;

import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.Presenter;

import javax.inject.Inject;

import static com.task.utils.Constants.NEWS_ITEM_KEY;
import static com.task.utils.ObjectUtil.isNull;

/**
 * Created by AhmedEltaher on 11/12/16.
 */

public class DetailsPresenter extends Presenter<DetailsContract.View> implements DetailsContract.Presenter {

    NewsItem newsItem;

    @Inject
    public DetailsPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        newsItem = extras.getParcelable(NEWS_ITEM_KEY);
        getView().initializeView(newsItem);
    }

    @Override
    public String getMainImageURL() {

        String url = null;
        if (!isNull(newsItem.getMultimedia()) && !newsItem.getMultimedia().isEmpty()) {
            String mainImageURL = newsItem.getMultimedia().get(newsItem.getMultimedia().size() - 1).getUrl();
            url = mainImageURL.equals("") ? null : mainImageURL;
        }
        return url;
    }
}
