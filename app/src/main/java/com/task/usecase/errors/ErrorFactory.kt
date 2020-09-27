package com.task.usecase.errors

import com.task.data.error.Error

interface ErrorFactory {
    fun getError(errorCode: Int): Error
}
