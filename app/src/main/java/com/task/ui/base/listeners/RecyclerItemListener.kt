package com.task.ui.base.listeners

import com.task.data.remote.dto.NewsItem

/**
 * Created by AhmedEltaher
 */

interface RecyclerItemListener {
    fun onItemSelected(newsItem: NewsItem)
}
