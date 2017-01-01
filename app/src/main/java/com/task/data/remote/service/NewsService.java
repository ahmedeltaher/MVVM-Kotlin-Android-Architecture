package com.task.data.remote.service;

import com.task.data.remote.dto.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public interface NewsService {
    @GET("topstories/v2/home.json")
    Call<NewsModel> fetchNews();
}
