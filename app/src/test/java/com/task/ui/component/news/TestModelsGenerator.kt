package com.task.ui.component.news

import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import java.util.*

/**
 * Created by ahmedEltaher on 3/8/17.
 */

class TestModelsGenerator {

    fun generateNewsModel(stup: String): NewsModel {
        val newsModel = NewsModel()
        newsModel.copyright = stup
        newsModel.lastUpdated = stup
        newsModel.section = stup
        newsModel.status = stup
        newsModel.numResults = 25L
        val newsItems = ArrayList<NewsItem>()
        for (i in 0..24) {
            newsItems.add(generateNewsItemModel(stup))
        }
        newsModel.newsItems = newsItems
        return newsModel
    }

    fun generateNewsModelWithEmptyList(stup: String): NewsModel {
        val newsModel = NewsModel()
        newsModel.copyright = stup
        newsModel.lastUpdated = stup
        newsModel.section = stup
        newsModel.status = stup
        newsModel.numResults = 25L
        val newsItems = ArrayList<NewsItem>()
        newsModel.newsItems = newsItems
        return newsModel
    }

    fun generateNewsItemModel(stup: String): NewsItem {
        val newsItem = NewsItem()
        newsItem.title = stup
        newsItem.abstract = stup
        newsItem.url = stup
        return newsItem
    }
}
