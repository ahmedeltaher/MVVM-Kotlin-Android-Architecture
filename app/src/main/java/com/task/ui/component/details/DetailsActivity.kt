package com.task.ui.component.details

import android.os.Bundle
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.remote.dto.NewsItem
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.utils.Constants
import kotlinx.android.synthetic.main.details_layout.*
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 11/12/16.
 */

class DetailsActivity : BaseActivity(){

    @Inject
    lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val layoutId: Int
        get() = R.layout.details_layout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.newsItem?.value = intent.getParcelableExtra(Constants.NEWS_ITEM_KEY)
        viewModel.newsItem?.observe(this, Observer
        { newsModel ->
            initializeView(newsItem = newsModel)
        })

    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(viewModel::class.java)
    }

    private fun initializeView(newsItem: NewsItem) {
        tv_title?.text = newsItem.title
        tv_description?.text = newsItem.abstractInfo
        if (!newsItem.multimedia.isNullOrEmpty()) {
            Picasso.get().load(newsItem.multimedia.last().url).placeholder(R.drawable.news)
                    .into(iv_news_main_Image)
        }
    }
}
