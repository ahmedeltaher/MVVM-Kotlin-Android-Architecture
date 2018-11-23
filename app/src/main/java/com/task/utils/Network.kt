package com.task.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by AhmedEltaher on 06/11/16.
 */

class Network {

    companion object Utils {
        private fun getNetworkInfo(context: Context): NetworkInfo? {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

        fun isConnected(context: Context): Boolean {
            val info = getNetworkInfo(context)
            return info != null && info.isConnected
        }
    }
}

