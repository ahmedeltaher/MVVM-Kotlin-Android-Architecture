package com.task.ui.base

import androidx.lifecycle.ViewModel
import com.task.data.error.mapper.ErrorMapper
import com.task.usecase.errors.ErrorManager


/**
 * Created by AhmedEltaher
 */


abstract class BaseViewModel(private val errorMapper: ErrorMapper) : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    val errorManager: ErrorManager = ErrorManager(errorMapper)
}
