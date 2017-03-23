package com.task.ui.component.details;

import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.listeners.BaseView;

/**
 * Created by AhmedEltaher on 11/12/16.
 */

public interface DetailsContract extends BaseView {
    interface View extends BaseView {
        void initializeView(NewsItem newsItem);
    }

    interface Presenter {
        String getMainImageURL();
    }
}
