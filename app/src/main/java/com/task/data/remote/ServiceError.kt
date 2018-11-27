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
        const val NETWORK_ERROR = "Unknown ServiceError"
        private const val GROUP_200 = 2
        private const val GROUP_400 = 4
        private const val GROUP_500 = 5
        private const val VALUE_100 = 100
        const val SUCCESS_CODE = 200
        const val ERROR_CODE = 400

        fun isClientError(errorCode: Int): Boolean {
            return errorCode / VALUE_100 == GROUP_400
        }

    }
}
