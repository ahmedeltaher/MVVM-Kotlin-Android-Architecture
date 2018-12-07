package com.task.ui.component.news

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.IdlingResource
import butterknife.OnClick
import com.task.App
import com.task.R
import com.task.data.remote.dto.NewsItem
import com.task.ui.base.BaseActivity
import com.task.ui.component.details.DetailsActivity
import com.task.utils.Constants
import com.task.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.home_activity.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class HomeActivity : BaseActivity(), HomeContract.View {
    @Inject
    lateinit var homePresenter: HomePresenter

    override val layoutId: Int
        get() = R.layout.home_activity

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource

    override fun initializeDagger() {
        val app = applicationContext as App
        app.mainComponent?.inject(this@HomeActivity)
    }

    override fun initializePresenter() {
        homePresenter.setView(this)
        super.presenter = homePresenter

    }

    override fun initializeNewsList(news: List<NewsItem>) {
        val newsAdapter = NewsAdapter(homePresenter.getRecyclerItemListener(), news)
        val layoutManager = LinearLayoutManager(this)
        rv_news_list.layoutManager = layoutManager
        rv_news_list.setHasFixedSize(true)
        rv_news_list.adapter = newsAdapter
    }

    override fun setLoaderVisibility(isVisible: Boolean) {
        pb_loading.visibility = if (isVisible) VISIBLE else GONE
    }

    override fun navigateToDetailsScreen(news: NewsItem) {
        startActivity(intentFor<DetailsActivity>(Constants.NEWS_ITEM_KEY to news))
    }

    override fun setNoDataVisibility(isVisible: Boolean) {
        tv_no_data.visibility = if (isVisible) VISIBLE else GONE
    }

    override fun setListVisibility(isVisible: Boolean) {
        rl_news_list.visibility = if (isVisible) VISIBLE else GONE
    }

    override fun showSearchError() {
        rl_news_list.snackbar(R.string.search_error)
    }

    override fun showNoNewsError() {
        rl_news_list.snackbar(R.string.news_error)
    }

    override fun incrementCountingIdlingResource() {
        EspressoIdlingResource.increment()
    }

    override fun decrementCountingIdlingResource() {
        EspressoIdlingResource.decrement()
    }

    @OnClick(R.id.ic_toolbar_setting, R.id.ic_toolbar_refresh, R.id.btn_search)
    fun onClick(view: View) {
        when (view.id) {
            R.id.ic_toolbar_refresh -> homePresenter.getNews()
            R.id.btn_search -> homePresenter.onSearchClick(et_search.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        homePresenter.unSubscribe()
    }
}
