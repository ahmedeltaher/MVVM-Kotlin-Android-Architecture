package com.task.data.remote

import io.reactivex.Single

/**
 * Created by ahmedeltaher on 3/23/17.
 */

internal interface RemoteSource {
    val news: Single<*>
}
