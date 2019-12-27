package com.task.data.remote

import com.task.App
import com.task.data.Resource
import com.task.data.remote.Error.Companion.ErrorsMap
import com.task.data.remote.Error.Companion.NETWORK_ERROR
import com.task.data.remote.Error.Companion.NO_INTERNET_CONNECTION
import com.task.data.remote.dto.NewsModel
import com.task.data.remote.service.NewsService
import com.task.utils.Constants
import com.task.utils.Constants.INSTANCE.ERROR_UNDEFINED
import com.task.utils.Network.Utils.isConnected
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator) : RemoteSource {

    override suspend fun requestNews(): Resource<NewsModel> {
        val newsService = serviceGenerator.createService(NewsService::class.java, Constants.BASE_URL)
        return when (val response = processCall(newsService::fetchNews, false)) {
            is Data -> {
                Resource.Success(data = response.data as NewsModel)
            }
            else -> {
                Resource.DataError(error = response as Error)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>, isVoid: Boolean): Any {
        if (!isConnected(App.context)) {
            return Error(code = NO_INTERNET_CONNECTION, description = ErrorsMap[NO_INTERNET_CONNECTION]
                    ?: "")
        }
        try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            /**
             * isVoid is for APIs which reply only with code without any body, such as some Apis
             * reply with 200 or 401....
             */
            return if (response.isSuccessful) {
                val apiResponse: Any? = if (isVoid) null else response.body()
                Data(responseCode, apiResponse)
            } else {
                Error(response.message(), responseCode)
            }
        } catch (e: IOException) {
            return Error(NETWORK_ERROR, ERROR_UNDEFINED)
        }

    }
}
