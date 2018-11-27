package com.task.data.remote

import android.accounts.NetworkErrorException
import com.task.App
import com.task.data.remote.dto.NewsModel
import com.task.data.remote.service.NewsService
import com.task.utils.Constants
import com.task.utils.Constants.INSTANCE.ERROR_UNDEFINED
import com.task.utils.L
import com.task.utils.Network.Utils.isConnected
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator) : RemoteSource {

    override val news: Single<*>
        get() {
            RxJavaPlugins.setErrorHandler { throwable ->
                L.i(RemoteRepository::class.java.simpleName, "${throwable.message}")
            }
            return Single.create<NewsModel> { singleOnSubscribe ->
                if (!isConnected(App.context)) {
                    val exception = NetworkErrorException()
                    singleOnSubscribe.onError(exception)
                } else {
                    try {
                        val newsService = serviceGenerator.createService(NewsService::class.java, Constants.BASE_URL)
                        val serviceResponse = processCall(newsService.fetchNews(), false)
                        if (serviceResponse.code == ServiceError.SUCCESS_CODE) {
                            val newsModel: NewsModel = serviceResponse.data as NewsModel
                            singleOnSubscribe.onSuccess(newsModel)
                        } else {
                            val throwable = NetworkErrorException()
                            singleOnSubscribe.onError(throwable)
                        }
                    } catch (e: Exception) {
                        singleOnSubscribe.onError(e)
                    }

                }
            }
        }

    private fun processCall(call: Call<*>, isVoid: Boolean): ServiceResponse {
        if (!isConnected(App.context)) {
            return ServiceResponse(ServiceError())
        }
        try {
            val response = call.execute() ?: return ServiceResponse(ServiceError(ServiceError.NETWORK_ERROR, ERROR_UNDEFINED))
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
