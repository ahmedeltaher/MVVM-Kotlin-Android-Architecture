package com.task.data;

import com.task.data.remote.dto.NewsModel;

import io.reactivex.Observable;

/**
 * Created by ahmedeltaher on 3/23/17.
 */

interface DataSource {
    Observable<NewsModel> requestNews();
}
