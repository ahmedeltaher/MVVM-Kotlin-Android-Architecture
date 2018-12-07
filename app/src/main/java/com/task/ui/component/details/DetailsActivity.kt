package com.task.ui.component.details

import com.squareup.picasso.Picasso
import com.task.App
import com.task.R
import com.task.data.remote.dto.NewsItem
import com.task.ui.base.BaseActivity
import kotlinx.android.synthetic.main.details_layout.*
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 11/12/16.
 */

class DetailsActivity : BaseActivity(), DetailsContract.View {

    @Inject
    lateinit var detailsPresenter: DetailsPresenter

    override val layoutId: Int
        get() = R.layout.details_layout

    override fun initializeDagger() {
        val app = applicationContext as App
        app.mainComponent?.inject(this@DetailsActivity)
    }

    override fun initializePresenter() {
        detailsPresenter.setView(this)
        super.presenter = detailsPresenter
    }

    override fun initializeView(newsItem: NewsItem) {
        tv_title?.text = newsItem.title ?: ""
        tv_description?.text = newsItem.abstract ?: ""
        if (!newsItem.multimedia.isNullOrEmpty()) {
            Picasso.get().load(newsItem.multimedia!!.last().url).placeholder(R.drawable.news)
                    .into(iv_news_main_Image)
        }
    }
}
