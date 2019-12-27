package com.task.data

import com.task.data.remote.dto.NewsModel

/**
 * Created by ahmedeltaher on 3/23/17.
 */

interface DataSource {
    suspend fun requestNews(): Resource<NewsModel>
}
