package com.task.ui.base.listeners

import com.task.data.remote.Error
import com.task.data.remote.dto.NewsModel

/**
 * Created by ahmedeltaher on 3/22/17.
 */

interface BaseCallback {
    fun onSuccess(data: NewsModel)

    fun onFail(error : Error?)
}
