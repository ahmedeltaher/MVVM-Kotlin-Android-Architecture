package com.task.data.remote

import java.lang.Exception

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class Error {
    var description: String? = ""
    var code: Int = -1

    constructor()

    constructor(description: String, code: Int) {
        this.description = description
        this.code = code
    }
    constructor(exception: Exception) {
        this.description = exception.message
        this.code = 500
    }

    companion object {
        const val NETWORK_ERROR = "Unknown Error"
        private const val GROUP_200 = 2
        private const val GROUP_400 = 4
        private const val GROUP_500 = 5
        private const val VALUE_100 = 100
        const val SUCCESS_CODE = 200
        const val ERROR_CODE = 400

        fun isClientError(errorCode: Int): Boolean {
            return errorCode / VALUE_100 == GROUP_400
        }

        val ErrorsMap = mapOf(
                Pair(-1, "Please check your internet connection")
        )
    }
}
