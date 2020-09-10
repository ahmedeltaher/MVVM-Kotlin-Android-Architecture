package com.task.data.error.mapper

import com.task.App
import com.task.R
import com.task.data.error.*
import javax.inject.Inject

class ErrorMapper @Inject constructor() : ErrorMapperInterface {

    override fun getErrorString(errorId: Int): String {
        return App.context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
                Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
                Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
                Pair(PASS_WORD_ERROR, getErrorString(R.string.invalid_password)),
                Pair(USER_NAME_ERROR, getErrorString(R.string.invalid_username)),
                Pair(CHECK_YOUR_FIELDS, getErrorString(R.string.invalid_username_and_password)),
                Pair(SEARCH_ERROR, getErrorString(R.string.search_error))
        ).withDefault { getErrorString(R.string.network_error) }
}
