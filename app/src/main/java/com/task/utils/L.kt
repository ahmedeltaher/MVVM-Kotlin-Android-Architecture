package com.task.utils

import com.task.BuildConfig
import org.jetbrains.anko.*

/**
 * Created by AhmedEltaher on 01/01/17.
 */

class L {
    companion object INSTANCE : AnkoLogger {

        fun d(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                AnkoLogger(tag).debug { massage }
            }
        }

        fun i(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                AnkoLogger(tag).info { massage }
            }
        }

        fun v(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                AnkoLogger(tag).verbose { massage }
            }
        }

        fun e(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                AnkoLogger(tag).error { massage }
            }
        }

        fun json(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                AnkoLogger(tag).info { massage }
            }
        }
    }
}
