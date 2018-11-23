package com.task.utils

import android.graphics.drawable.Drawable
import android.os.Build
import com.task.App

/**
 * Created by AhmedEltaher on 05/12/16.
 */

class ResourcesUtil {
    companion object INSTANCE {
        private val context = App.context
        private val theme = App.context?.theme

        fun getDrawableById(resId: Int): Drawable {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                context!!.resources.getDrawable(resId, theme)
            else
                context!!.resources.getDrawable(resId)
        }

        fun getString(resId: Int): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                context!!.resources.getString(resId)
            else
                context!!.resources.getString(resId)
        }
    }
}
