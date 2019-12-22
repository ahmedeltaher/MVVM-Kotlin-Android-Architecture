package com.task.data.remote

import com.task.data.DataStatus

/**
 * Created by ahmedEltaher on 3/23/17.
 */

internal interface RemoteSource {
    fun requestNews(): DataStatus
}
