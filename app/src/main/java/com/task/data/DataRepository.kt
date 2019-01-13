package com.task.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.local.LocalRepository
import com.task.data.remote.RemoteRepository
import com.task.data.remote.ServiceResponse
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class DataRepository @Inject
constructor(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository) : DataSource {

    override suspend fun requestNews(): LiveData<ServiceResponse>? {
        val newsLiveData: MutableLiveData<ServiceResponse> = MutableLiveData()
        newsLiveData.value=remoteRepository.requestNews()
        return newsLiveData
    }

}
