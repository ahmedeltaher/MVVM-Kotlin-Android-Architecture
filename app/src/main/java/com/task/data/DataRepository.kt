package com.task.data

import com.task.data.local.LocalData
import com.task.data.remote.RemoteData
import com.task.data.remote.dto.NewsModel
import com.task.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


/**
 * Created by AhmedEltaher
 */

class DataRepository @Inject
constructor(private val remoteRepository: RemoteData, private val localRepository: LocalData) : DataRepositorySource {

    override suspend fun requestNews(): Flow<Resource<NewsModel>> {
        return flow {
//                emit(Resource.Loading())
                emit(remoteRepository.requestNews())
            }.flowOn(Dispatchers.IO)
    }
}
