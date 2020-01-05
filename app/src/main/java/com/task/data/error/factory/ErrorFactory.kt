package com.task.data.error.factory

import com.task.data.error.Error

interface ErrorFactory {
    fun getError(errorCode: Int): Error
}