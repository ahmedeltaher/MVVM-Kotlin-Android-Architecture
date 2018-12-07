package com.task.data

import io.reactivex.Single

/**
 * Created by ahmedeltaher on 3/23/17.
 */

internal interface DataSource {
    fun requestNews(): Single<*>
}
