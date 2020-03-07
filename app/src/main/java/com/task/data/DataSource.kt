package com.task.data

import com.task.data.remote.dto.NewsModel

/**
 * Created by AhmedEltaher
 */

interface DataSource {
    suspend fun requestNews(): Resource<NewsModel>
}
