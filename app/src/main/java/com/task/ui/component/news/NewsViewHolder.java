package com.task.ui.component.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.task.R;
import com.task.data.remote.dto.NewsItem;
import com.task.ui.base.listeners.RecyclerItemListener;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.TextUtils.isEmpty;
import static com.task.utils.ObjectUtil.isNull;
import static com.task.utils.ResourcesUtil.getDrawableById;

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private RecyclerItemListener onItemClickListener;

    @BindView(R.id.tv_caption)
    TextView tvCaption;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_news_item)
    RelativeLayout newsItemLayout;
    @BindView(R.id.iv_news_item_image)
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
        Picasso.get().load(URL).placeholder(getDrawableById(R.drawable.news)).into(newsImage);
        newsItemLayout.setOnClickListener(v -> recyclerItemListener.onItemSelected(position));
    }
}

