package com.task.ui.component.news

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.IdlingResource
import com.task.R
import com.task.data.remote.dto.NewsItem
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.news.newsAdapter.NewsAdapter
import com.task.utils.Constants
import com.task.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsListActivity : BaseActivity(), RecyclerItemListener {
    @Inject
    lateinit var newsListViewModel: NewsListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override val layoutId: Int
        get() = R.layout.home_activity

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    override fun initializeViewModel() {
        newsListViewModel = viewModelFactory.create(NewsListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ic_toolbar_refresh.setOnClickListener {
            getNews()
        }
        btn_search.setOnClickListener {
            if (!(et_search.text?.toString().isNullOrEmpty())) {
                pb_loading.visibility = VISIBLE
                newsListViewModel.onSearchClick(et_search.text?.toString()!!)
            }
        }
        initializeNewsList()
        init(newsListViewModel)
    }

    private fun init(viewModel: NewsListViewModel) {
        viewModel.noInterNetConnection.observe(this, Observer {
            if (it) {
                tv_no_data.visibility = VISIBLE
                rl_news_list.visibility = GONE
                toast("Please check your Internet connection!")
                pb_loading.visibility = GONE
            }
        })

        viewModel.showError.observe(this, Observer {
            showDataView(false)
            toast("" + it?.description)
        })

        newsListViewModel.newsSearchFound.observe(this, Observer { newsItem ->
            if (newsItem != null) {
                navigateToDetailsScreen(newsItem)
            } else {
                showSearchError()
            }
            pb_loading.visibility = GONE
        })

        viewModel.newsModel.observe(this, Observer { newsModel ->
            // we don't need any null checks here for the adapter since LiveData guarantees that
            if (!(newsModel?.newsItems.isNullOrEmpty())) {
                val newsAdapter = NewsAdapter(this, newsModel?.newsItems!!)
                rv_news_list.adapter = newsAdapter
                showDataView(true)
            } else {
                showDataView(false)
                toast("some thing went wrong!")
            }
            EspressoIdlingResource.decrement()
        })
        getNews()
    }

    private fun initializeNewsList() {
        val layoutManager = LinearLayoutManager(this)
        rv_news_list.layoutManager = layoutManager
        rv_news_list.setHasFixedSize(true)
    }

    override fun onItemSelected(position: Int) =
            this.navigateToDetailsScreen(news = newsListViewModel.newsModel.value?.newsItems?.get(position)!!)

    private fun getNews() {
        pb_loading.visibility = VISIBLE
        tv_no_data.visibility = GONE
        rl_news_list.visibility = GONE
        EspressoIdlingResource.increment()
        newsListViewModel.getNews()
    }

    private fun navigateToDetailsScreen(news: NewsItem) {
        startActivity(intentFor<DetailsActivity>(Constants.NEWS_ITEM_KEY to news))
    }

    private fun showSearchError() {
        rl_news_list.snackbar(R.string.search_error)
    }

    private fun showDataView(show: Boolean) {
        tv_no_data.visibility = if (show) GONE else VISIBLE
        rl_news_list.visibility = if (show) VISIBLE else GONE
        pb_loading.visibility = GONE
    }
}
