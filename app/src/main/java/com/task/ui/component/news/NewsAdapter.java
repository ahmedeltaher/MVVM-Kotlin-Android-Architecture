package com.task.ui.component.news;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.task.R;
import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.listeners.RecyclerItemListener;

import java.util.List;

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private final List<NewsItem> news;
    private RecyclerItemListener onItemClickListener;

    public NewsAdapter(RecyclerItemListener onItemClickListener, List<NewsItem> news) {
        this.onItemClickListener = onItemClickListener;
        this.news = news;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(position, news.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}

