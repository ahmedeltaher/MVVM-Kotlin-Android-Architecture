package com.task.data.remote

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class Error {
    var description: String? = ""
    var code: Int = NO_INTERNET_CONNECTION

    constructor()

    constructor(description: String = ErrorsMap.getValue(NO_INTERNET_CONNECTION), code: Int = NO_INTERNET_CONNECTION) {
        this.description = description
        this.code = code
    }

    constructor(exception: Exception) {
        this.description = exception.message
        this.code = INTERNAL_SERVER_ERROR
    }

    companion object {
        const val NETWORK_ERROR = "Unknown Error"
        private const val GROUP_200 = 2
        private const val GROUP_400 = 4
        private const val GROUP_500 = 5
        const val INTERNAL_SERVER_ERROR = 500
        const val NO_INTERNET_CONNECTION = -1
        private const val VALUE_100 = 100
        const val SUCCESS_CODE = 200
        const val ERROR_CODE = 400

        fun isClientError(errorCode: Int): Boolean {
            return errorCode / VALUE_100 == GROUP_400
        }

        val ErrorsMap = mapOf(
                Pair(NO_INTERNET_CONNECTION, "Please check your internet connection")
        )
    }
}
