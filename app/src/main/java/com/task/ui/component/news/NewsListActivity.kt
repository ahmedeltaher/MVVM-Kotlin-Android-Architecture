package com.task.ui.component.news

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.data.Resource
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import com.task.databinding.HomeActivityBinding
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.news.newsAdapter.NewsAdapter
import com.task.utils.*
import com.task.utils.Constants.INSTANCE.NEWS_ITEM_KEY
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

class NewsListActivity : BaseActivity() {
    private lateinit var binding: HomeActivityBinding

    @Inject
    lateinit var newsListViewModel: NewsListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun initViewBinding() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun initializeViewModel() {
        newsListViewModel = viewModelFactory.create(NewsListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.news)
        val layoutManager = LinearLayoutManager(this)
        binding.rvNewsList.layoutManager = layoutManager
        binding.rvNewsList.setHasFixedSize(true)
        newsListViewModel.getNews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_actions, menu)
        // Associate searchable configuration with the SearchView
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_by_title)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                handleSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> newsListViewModel.getNews()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleSearch(query: String) {
        if (query.isNotEmpty()) {
            binding.pbLoading.visibility = VISIBLE
            newsListViewModel.onSearchClick(query)
        }
    }


    private fun bindListData(newsModel: NewsModel) {
        if (!(newsModel.results.isNullOrEmpty())) {
            val newsAdapter = NewsAdapter(newsListViewModel, newsModel.results)
            binding.rvNewsList.adapter = newsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
        EspressoIdlingResource.decrement()
    }

    private fun navigateToDetailsScreen(navigateEvent: Event<NewsItem>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(NEWS_ITEM_KEY, it)
            }
            startActivity(nextScreenIntent)
        }
    }

    private fun observeSnackBarMessages(event: LiveData<Event<Int>>) {
        binding.rlNewsList.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<Event<Any>>) {
        binding.rlNewsList.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showSearchError() {
        newsListViewModel.showSnackbarMessage(R.string.search_error)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) GONE else VISIBLE
        binding.rlNewsList.visibility = if (show) VISIBLE else GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rlNewsList.toGone()
        EspressoIdlingResource.increment()
    }


    private fun showSearchResult(newsItem: NewsItem) {
        newsListViewModel.openNewsDetails(newsItem)
        binding.pbLoading.toGone()
    }

    private fun noSearchResult(unit: Unit) {
        showSearchError()
        binding.pbLoading.toGone()
    }

    private fun handleNewsList(newsModel: Resource<NewsModel>) {
        when (newsModel) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> newsModel.data?.let { bindListData(newsModel = it) }
            is Resource.DataError -> {
                showDataView(false)
                newsModel.errorCode?.let { newsListViewModel.showToastMessage(it) }
            }
        }

    }

    override fun observeViewModel() {
        observe(newsListViewModel.newsLiveData, ::handleNewsList)
        observe(newsListViewModel.newsSearchFound, ::showSearchResult)
        observe(newsListViewModel.noSearchFound, ::noSearchResult)
        observeEvent(newsListViewModel.openNewsDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(newsListViewModel.showSnackBar)
        observeToast(newsListViewModel.showToast)

    }
}
