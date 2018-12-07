package com.task.ui.component.details

import com.task.data.remote.dto.NewsItem
import com.task.ui.base.listeners.BaseView

/**
 * Created by AhmedEltaher on 11/12/16.
 */

interface DetailsContract : BaseView {
    interface View : BaseView {
        fun initializeView(newsItem: NewsItem)
    }

    interface Presenter
}
