package com.task.ui.component.details

import android.os.Bundle
import com.squareup.picasso.Picasso
import com.task.R
import com.task.data.remote.dto.NewsItem
import com.task.databinding.DetailsLayoutBinding
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.utils.Constants
import com.task.utils.observe
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

class DetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: DetailsLayoutBinding


    override fun initViewBinding() {
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.top_news)
        viewModel.newsItem.value = intent.getParcelableExtra(Constants.NEWS_ITEM_KEY)

    }

    override fun observeViewModel() {
        observe(viewModel.newsItem, ::initializeView)
    }

    override fun initializeViewModel() {
        viewModel = viewModelFactory.create(viewModel::class.java)
    }

    private fun initializeView(newsItem: NewsItem) {
        binding.tvTitle.text = newsItem.title
        binding.tvDescription.text = newsItem.abstractInfo
        if (!newsItem.multimedia.isNullOrEmpty()) {
            Picasso.get().load(newsItem.multimedia[0]?.url).placeholder(R.drawable.news)
                    .into(binding.ivNewsMainImage)
        }
    }
}
