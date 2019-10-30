package com.task.data.remote

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class Data(var code: Int = 0, var error: Error? = null, var data: Any? = null) {

    constructor(errorCode: Int, dataObject: Any?) : this(errorCode, data = dataObject)

    constructor(errorObject: Error) : this(error = errorObject)

    constructor(dataObject: Any) : this(data = dataObject)
}
