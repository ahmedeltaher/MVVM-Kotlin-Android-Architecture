package com.task.data.remote.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Multimedia(
    var caption: String = "",
    var copyright: String = "",
    var format: String = "",
    var height: Int = 0,
    var subtype: String = "",
    var type: String = "",
    var url: String = "",
    var width: Int = 0
):Parcelable