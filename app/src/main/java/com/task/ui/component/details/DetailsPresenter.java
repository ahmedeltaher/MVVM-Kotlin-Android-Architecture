package com.task.ui.component.details;

import android.os.Bundle;

import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.Presenter;
import com.task.utils.Constants;
import com.task.utils.ObjectUtil;

import javax.inject.Inject;

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
        newsItem = extras.getParcelable(Constants.NEWS_ITEM_KEY);
        getView().initializeView(newsItem);
    }

    @Override
    public String getMainImageURL() {

        String url = null;
        if (!ObjectUtil.INSTANCE.isNull(newsItem.getMultimedia()) && !newsItem.getMultimedia().isEmpty()) {
            String mainImageURL = newsItem.getMultimedia().get(newsItem.getMultimedia().size() - 1).getUrl();
            url = mainImageURL.equals("") ? null : mainImageURL;
        }
        return url;
    }
}
