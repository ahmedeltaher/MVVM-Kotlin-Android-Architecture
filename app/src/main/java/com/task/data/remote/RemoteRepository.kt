package com.task.data.remote

import com.task.App
import com.task.data.Resource
import com.task.data.error.Error
import com.task.data.error.Error.Companion.NETWORK_ERROR
import com.task.data.error.Error.Companion.NO_INTERNET_CONNECTION
import com.task.data.error.factory.ErrorFactoryImpl
import com.task.data.remote.dto.NewsModel
import com.task.data.remote.service.NewsService
import com.task.utils.Constants
import com.task.utils.Network.Utils.isConnected
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val errors: ErrorFactoryImpl) : RemoteSource {

    override suspend fun requestNews(): Resource<NewsModel> {
        val newsService = serviceGenerator.createService(NewsService::class.java, Constants.BASE_URL)
        return when (val response = processCall(newsService::fetchNews)) {
            is NewsModel -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(error = response as Error)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!isConnected(App.context)) {
            return errors.getError(NO_INTERNET_CONNECTION)
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                errors.getError(responseCode)
            }
        } catch (e: IOException) {
            errors.getError(NETWORK_ERROR)
        }
    }
}
