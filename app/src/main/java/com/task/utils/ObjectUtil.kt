package com.task.utils

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

class ObjectUtil {
    companion object INSTANCE {
        fun isEmpty(string: String?): Boolean {
            return string == null || string.length == 0
        }

        fun isNull(obj: Any?): Boolean {
            return obj == null
        }

        fun isEmptyList(list: List<*>?): Boolean {
            return list == null || list.isEmpty()
        }
    }
}
