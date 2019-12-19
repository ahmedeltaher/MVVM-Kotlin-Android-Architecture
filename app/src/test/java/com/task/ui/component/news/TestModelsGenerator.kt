package com.task.ui.component.news

import com.google.gson.Gson
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import java.io.File
import java.util.*

/**
 * Created by ahmedEltaher on 3/8/17.
 */

class TestModelsGenerator {
    private var newsModel: NewsModel

    init {
        val gson = Gson()
        val jsonString = getJson("NewsApiResponse.json")
        newsModel = gson.fromJson(jsonString, NewsModel::class.java)
    }

    fun generateNewsModel(): NewsModel {
        return newsModel
    }

    fun generateNewsModelWithEmptyList(): NewsModel {
        newsModel.newsItems = ArrayList()
        return newsModel
    }

    fun generateNewsItemModel(): NewsItem {
        return newsModel.newsItems[0]
    }

    fun getStupSearchTitle(): String {
        return newsModel.newsItems[0].title
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}
