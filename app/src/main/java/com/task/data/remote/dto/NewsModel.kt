package com.task.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsModel(@SerializedName("status") var status: String? = null,
                     @SerializedName("copyright") var copyright: String? = null,
                     @SerializedName("section") var section: String? = null,
                     @SerializedName("last_updated") var lastUpdated: String? = null,
                     @SerializedName("num_results") var numResults: Long? = null,
                     @SerializedName("results") var newsItems: List<NewsItem>? = null)
