package com.task.ui.component.news.newsAdapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.remote.dto.NewsItem
import com.task.databinding.NewsItemBinding
import com.task.ui.base.listeners.RecyclerItemListener

/**
 * Created by AhmedEltaher
 */

class NewsViewHolder(private val itemBinding: NewsItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(newsItem: NewsItem, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvCaption.text = newsItem.abstractInfo
        itemBinding.tvTitle.text = newsItem.title
        newsItem.multimedia?.let{
            if (it.size > 3) {
                val url: String? = it[3]?.url
                Picasso.get().load(url).placeholder(R.drawable.news).error(R.drawable.news).into(itemBinding.ivNewsItemImage)
            }
        }
        itemBinding.rlNewsItem.setOnClickListener { recyclerItemListener.onItemSelected(newsItem) }
    }
}

