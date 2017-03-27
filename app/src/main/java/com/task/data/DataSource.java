package com.task.data;

import com.task.data.remote.dto.NewsModel;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by ahmedeltaher on 3/23/17.
 */

interface DataSource {
    Single<NewsModel> requestNews();
}
