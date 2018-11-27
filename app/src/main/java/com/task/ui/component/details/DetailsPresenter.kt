package com.task.ui.component.details

import android.os.Bundle
import com.task.data.remote.dto.NewsItem
import com.task.ui.base.Presenter
import com.task.utils.Constants
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 11/12/16.
 */

class DetailsPresenter @Inject
constructor() : Presenter<DetailsContract.View>(), DetailsContract.Presenter {

    private var newsItem: NewsItem? = null

    override fun initialize(extras: Bundle?) {
        super.initialize(extras)
        newsItem = extras?.getParcelable(Constants.NEWS_ITEM_KEY)
        getView()?.initializeView(newsItem!!)
    }
}
