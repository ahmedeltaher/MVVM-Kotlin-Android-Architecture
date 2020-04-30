package com.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.task.data.remote.dto.NewsItem
import com.task.data.remote.dto.NewsModel
import java.io.File

/**
 * Created by AhmedEltaher
 */

class TestModelsGenerator {
    private var newsModel: NewsModel = NewsModel()

    init {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<NewsModel> = moshi.adapter(NewsModel::class.java)
        val jsonString = getJson("NewsApiResponse.json")
        adapter.fromJson(jsonString)?.let {
            newsModel = it
        }
    }

    fun generateNewsModel(): NewsModel {
        return newsModel
    }

    fun generateNewsModelWithEmptyList(): NewsModel {
        return NewsModel()
    }

    fun generateNewsItemModel(): NewsItem {
        return newsModel.results[0]
    }

    fun getStupSearchTitle(): String {
        return newsModel.results[0].title
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
