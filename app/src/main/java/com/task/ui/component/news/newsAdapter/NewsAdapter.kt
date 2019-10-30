package com.task.ui.component.news.newsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.R
import com.task.data.remote.dto.NewsItem
import com.task.ui.base.listeners.RecyclerItemListener

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class NewsAdapter(private val onItemClickListener: RecyclerItemListener, private val news: List<NewsItem>) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(position, news[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return news.size
    }
}

