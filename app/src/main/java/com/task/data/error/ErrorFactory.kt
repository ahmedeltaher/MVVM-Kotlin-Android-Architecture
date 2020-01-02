package com.task.data.error

interface ErrorFactory {
    fun getError(errorCode: Int): Error
}