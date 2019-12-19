package com.task.ui.base.listeners

import com.task.data.remote.Error

/**
 * Created by ahmedeltaher on 3/22/17.
 */

interface BaseCallback {
    fun onSuccess(data: Any)

    fun onFail(error : Error)
}
