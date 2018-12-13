package com.task.data.remote.service

import com.task.data.remote.dto.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by AhmedEltaher on 5/12/2016
 */

interface NewsService {
    @GET("topstories/v2/home.json?api-key=4rfwOLzLTWd1a5xixcPjwddAhw3p0eiF")
    fun  fetchNews(): Call<NewsModel>
}
