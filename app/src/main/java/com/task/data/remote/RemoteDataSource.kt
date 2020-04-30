package com.task.data.remote

import com.task.data.Resource
import com.task.data.remote.dto.NewsModel

/**
 * Created by AhmedEltaher
 */

internal interface RemoteDataSource {
    suspend fun requestNews(): Resource<NewsModel>
}
