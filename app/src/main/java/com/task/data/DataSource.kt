package com.task.data

import com.task.data.remote.dto.NewsModel
import io.reactivex.Single

/**
 * Created by ahmedeltaher on 3/23/17.
 */

internal interface DataSource {
    fun requestNews(): Single<NewsModel>
}
