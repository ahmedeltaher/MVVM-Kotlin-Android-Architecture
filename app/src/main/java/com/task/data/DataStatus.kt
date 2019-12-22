package com.task.data

import com.task.data.remote.Data
import com.task.data.remote.Error

sealed class DataStatus {
    object LoadingState : DataStatus()
    class FailureState(val error: Error) : DataStatus()
    class SuccessState(val data: Data) : DataStatus()
}