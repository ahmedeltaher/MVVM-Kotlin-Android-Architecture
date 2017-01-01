package com.task.ui.component.details;

import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.Presenter;

/**
 * Created by AhmedEltaher on 11/12/16.
 */

public interface DetailsView extends Presenter.View {
    void initializeView(NewsItem newsItem);
}
