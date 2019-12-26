package com.task.data

import com.task.data.remote.Error

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
        val data: T? = null,
        val error: Error? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class DataError<T>(error: Error) : Resource<T>(null, error)
}