package com.task.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Multimedium(@SerializedName("url") var url: String? = null,
                       @SerializedName("format") var format: String? = null,
                       @SerializedName("height") var height: Long? = null,
                       @SerializedName("width") var width: Long? = null,
                       @SerializedName("type") var type: String? = null,
                       @SerializedName("subtype") var subtype: String? = null,
                       @SerializedName("caption") var caption: String? = null,
                       @SerializedName("copyright") var copyright: String? = null) : Parcelable

