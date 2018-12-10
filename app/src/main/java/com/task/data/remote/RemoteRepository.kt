package com.task.data.remote

import com.task.App
import com.task.data.remote.service.NewsService
import com.task.utils.Constants
import com.task.utils.Constants.INSTANCE.ERROR_UNDEFINED
import com.task.utils.Network.Utils.isConnected
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator) : RemoteSource {

    override suspend fun requestNews(): ServiceResponse? {
        return if (!isConnected(App.context)) {
            ServiceResponse(ServiceError(code = -1, description = ServiceError.NETWORK_ERROR))
        } else {
            val newsService = serviceGenerator.createService(NewsService::class.java, Constants.BASE_URL)
            processCall(newsService.fetchNews(), false)
        }
    }

    private fun processCall(call: Call<*>, isVoid: Boolean): ServiceResponse {
        if (!isConnected(App.context)) {
            return ServiceResponse(ServiceError())
        }
        try {
            val response = call.execute()
                    ?: return ServiceResponse(ServiceError(ServiceError.NETWORK_ERROR, ERROR_UNDEFINED))
            val responseCode = response.code()
            /**
             * isVoid is for APIs which reply only with code without any body, such as some Apis
             * reply with 200 or 401....
             */
            return if (response.isSuccessful) {
                val apiResponse: Any? = if (isVoid) null else response.body()
                ServiceResponse(responseCode, apiResponse)
            } else {
                val serviceError = ServiceError(response.message(), responseCode)
                ServiceResponse(serviceError)
            }
        } catch (e: IOException) {
            return ServiceResponse(ServiceError(ServiceError.NETWORK_ERROR, ERROR_UNDEFINED))
        }

    }
}
