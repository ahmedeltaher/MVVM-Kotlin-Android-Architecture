package com.task.data.remote

import io.reactivex.Single

/**
 * Created by ahmedEltaher on 3/23/17.
 */

internal interface RemoteSource {
    fun requestNews(): Single<Data>
}
