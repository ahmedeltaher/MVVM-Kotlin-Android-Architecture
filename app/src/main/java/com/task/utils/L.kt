package com.task.utils

import android.util.Log
import com.task.BuildConfig

/**
 * Created by AhmedEltaher
 */

class L {
    companion object INSTANCE {

        fun d(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                Log.d(tag, massage)
            }
        }

        fun i(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                Log.i(tag, massage)
            }
        }

        fun v(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                Log.v(tag, massage)
            }
        }

        fun e(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                Log.e(tag, massage)
            }
        }

        fun json(tag: String, massage: String) {
            if (BuildConfig.DEBUG) {
                Log.i(tag, massage)
            }
        }
    }
}
