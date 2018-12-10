package com.task.data

import com.task.data.remote.ServiceResponse

/**
 * Created by ahmedeltaher on 3/23/17.
 */

internal interface DataSource {
    suspend fun requestNews(): ServiceResponse?
}
