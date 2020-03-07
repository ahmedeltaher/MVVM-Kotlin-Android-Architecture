package com.task.ui.component.splash

import com.task.data.error.mapper.ErrorMapper
import com.task.ui.base.BaseViewModel
import com.task.usecase.errors.ErrorManager
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

class SplashViewModel @Inject
constructor() : BaseViewModel(){
    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())
}
