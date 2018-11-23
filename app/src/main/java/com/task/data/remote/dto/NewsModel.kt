package com.task.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * we define all the variables as @var, because it might happen that backend send some values as
 * null
 */
/**
 * you can convert your json to kotlin data class in easy simple way, by using
 * @(JSON To Kotlin Class) plugin in android studio, you can install the plugin as any other
 * plugin and then use it, for more details check here:
 * https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
 */
data class NewsModel(@SerializedName("status") var status: String? = null,
                     @SerializedName("copyright") var copyright: String? = null,
                     @SerializedName("section") var section: String? = null,
                     @SerializedName("last_updated") var lastUpdated: String? = null,
                     @SerializedName("num_results") var numResults: Long? = null,
                     @SerializedName("results") var newsItems: List<NewsItem>? = null)
