package com.task.data.remote

import com.task.App
import com.task.data.Resource
import com.task.data.error.Error.Companion.NETWORK_ERROR
import com.task.data.error.Error.Companion.NO_INTERNET_CONNECTION
import com.task.data.remote.dto.NewsModel
import com.task.data.remote.service.NewsService
import com.task.utils.Network.Utils.isConnected
import com.task.utils.wrapEspressoIdlingResource
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by AhmedEltaher
 */

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator) : RemoteDataSource {

    override suspend fun requestNews(): Resource<NewsModel> {
        wrapEspressoIdlingResource {
            val newsService = serviceGenerator.createService(NewsService::class.java)
            return when (val response = processCall(newsService::fetchNews)) {
                is NewsModel -> {
                    Resource.Success(data = response)
                }
                else -> {
                    Resource.DataError(errorCode = response as Int)
                }
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!isConnected(App.context)) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
