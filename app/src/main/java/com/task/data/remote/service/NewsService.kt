package com.task.data.remote.service

import com.task.data.remote.dto.NewsModel
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by AhmedEltaher
 */

interface NewsService {
    @GET("topstories/v2/home.json?api-key=4rfwOLzLTWd1a5xixcPjwddAhw3p0eiF")
    suspend fun fetchNews(): Response<NewsModel>
}
