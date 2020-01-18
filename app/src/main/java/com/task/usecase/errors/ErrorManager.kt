package com.task.usecase.errors

import com.task.data.error.Error
import com.task.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorFactory {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }

}