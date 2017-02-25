package com.task.ui.component.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.task.R;
import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.listeners.RecyclerItemListener;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;
import static com.task.utils.ObjectUtil.isNull;
import static com.task.utils.ResourcesUtil.getDrawableById;

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private RecyclerItemListener onItemClickListener;

    @Bind(R.id.tv_caption)
    TextView tvCaption;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_news_item)
    RelativeLayout newsItemLayout;
    @Bind(R.id.iv_news_item_image)
    ImageView newsImage;


    public NewsViewHolder(View itemView, RecyclerItemListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position, NewsItem newsItem, RecyclerItemListener recyclerItemListener) {
        //need to move to mapper
        if (!isEmpty(newsItem.getAbstract())) {
            tvTitle.setText(newsItem.getTitle());
        }
        if (!isEmpty(newsItem.getTitle())) {
            tvCaption.setText(newsItem.getAbstract());
        }
        String URL = null;
        if (!isNull(newsItem.getMultimedia()) && newsItem.getMultimedia().size() > 3) {
            URL = newsItem.getMultimedia().get(3).getUrl();
        }
        Picasso.with(newsImage.getContext()).load(URL).placeholder(getDrawableById(R.drawable.news)).into(newsImage);
        newsItemLayout.setOnClickListener(v -> recyclerItemListener.onItemSelected(position));
    }
}

