package com.task.ui.base.listeners

import com.task.data.remote.dto.NewsItem

/**
 * Created by AhmedEltaher on 5/12/2016
 */

interface RecyclerItemListener {
    fun onItemSelected(newsItem: NewsItem)
}
