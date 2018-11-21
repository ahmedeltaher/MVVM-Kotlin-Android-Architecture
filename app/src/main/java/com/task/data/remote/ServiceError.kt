package com.task.data.remote

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class ServiceError {
    var description: String? = ""
    var code: Int = -1

    constructor()

    constructor(description: String, code: Int) {
        this.description = description
        this.code = code
    }

    companion object {
        val NETWORK_ERROR = "Unknown ServiceError"
        private val GROUP_200 = 2
        private val GROUP_400 = 4
        private val GROUP_500 = 5
        private val VALUE_100 = 100
        val SUCCESS_CODE = 200
        val ERROR_CODE = 400

        fun isSuccess(responseCode: Int): Boolean {
            return responseCode / VALUE_100 == GROUP_200
        }

        fun isClientError(errorCode: Int): Boolean {
            return errorCode / VALUE_100 == GROUP_400
        }

        fun isServerError(errorCode: Int): Boolean {
            return errorCode / VALUE_100 == GROUP_500
        }
    }
}
