package com.task.data

import com.task.data.remote.dto.NewsModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by AhmedEltaher
 */

interface DataRepositorySource {
    suspend fun requestNews(): Flow<Resource<NewsModel>>
}
