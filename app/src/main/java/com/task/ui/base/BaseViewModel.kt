package com.task.ui.base

import androidx.lifecycle.ViewModel
import com.task.usecase.errors.ErrorManager


/**
 * Created by AhmedEltaher
 */


abstract class BaseViewModel : ViewModel() {
    /**Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    abstract val errorManager: ErrorManager

}
