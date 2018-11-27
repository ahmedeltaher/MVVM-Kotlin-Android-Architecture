package com.task.data.remote

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class ServiceResponse(var code: Int = -1, var serviceError: ServiceError? = null, var data: Any? = null) {

    constructor(errorCode: Int, response: Any?) : this(errorCode, data = response)

    constructor(error: ServiceError) : this()

    constructor(response: Any) : this(data = response)
}
