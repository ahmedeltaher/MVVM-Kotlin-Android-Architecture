package com.task.data.remote

/**
 * Created by ahmedEltaher on 3/23/17.
 */

internal interface RemoteSource {
    suspend fun requestNews(): ServiceResponse?
}
