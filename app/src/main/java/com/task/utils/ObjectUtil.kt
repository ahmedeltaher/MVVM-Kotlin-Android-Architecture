package com.task.utils

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class ObjectUtil {
    companion object INSTANCE {
        fun isNull(obj: Any?): Boolean {
            return obj == null
        }
    }
}
