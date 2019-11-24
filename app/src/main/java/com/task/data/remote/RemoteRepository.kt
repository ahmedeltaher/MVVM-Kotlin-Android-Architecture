package com.task.data.remote

import com.task.App
import com.task.data.remote.Error.Companion.NETWORK_ERROR
import com.task.data.remote.service.NewsService
import com.task.utils.Constants
import com.task.utils.Constants.INSTANCE.ERROR_UNDEFINED
import com.task.utils.Network.Utils.isConnected
import io.reactivex.Single
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator) : RemoteSource {

    override fun requestNews(): Single<Data> {
        return Single.create {
            if (!isConnected(App.context)) {
                it.onError(Error(code = -1, description = NETWORK_ERROR))
            } else {
                val newsService = serviceGenerator.createService(NewsService::class.java, Constants.BASE_URL)
                val data = processCall(newsService.fetchNews(), false)
                it.onSuccess(data)
            }
        }
    }

    private fun processCall(call: Call<*>, isVoid: Boolean): Data {
        if (!isConnected(App.context)) {
            return Data(Error())
        }
        try {
            val response = call.execute()
                    ?: return Data(Error(NETWORK_ERROR, ERROR_UNDEFINED))
            val responseCode = response.code()
            /**
             * isVoid is for APIs which reply only with code without any body, such as some Apis
             * reply with 200 or 401....
             */
            return if (response.isSuccessful) {
                val apiResponse: Any? = if (isVoid) null else response.body()
                Data(responseCode, apiResponse)
            } else {
                val serviceError = Error(response.message(), responseCode)
                Data(serviceError)
            }
        } catch (e: IOException) {
            return Data(Error(NETWORK_ERROR, ERROR_UNDEFINED))
        }

    }
}
