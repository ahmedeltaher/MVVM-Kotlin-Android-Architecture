package com.task.data.error

import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class ErrorFactoryImpl @Inject constructor(private val errorMapper: ErrorMapper) : ErrorFactory {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }

}