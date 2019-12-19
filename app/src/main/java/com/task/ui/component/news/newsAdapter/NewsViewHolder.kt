package com.task.ui.component.news.newsAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.remote.dto.NewsItem
import com.task.ui.base.listeners.RecyclerItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class NewsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(position: Int, newsItem: NewsItem, recyclerItemListener: RecyclerItemListener) {
        tv_caption.text = newsItem.abstractInfo
        tv_title.text = newsItem.title

        if (newsItem.multimedia.size > 3) {
            val url: String? = newsItem.multimedia[3].url
            Picasso.get().load(url).placeholder(R.drawable.news).error(R.drawable.news).into(iv_news_item_image)
        }
        rl_news_item.setOnClickListener { recyclerItemListener.onItemSelected(position) }
    }
}

