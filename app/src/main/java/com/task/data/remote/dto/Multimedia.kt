package com.task.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * we define all the variables as @var, because it might happen that backend send some values as
 * null
 */
/**
 *@Parcelize is from kotlin experimental but it is stable, and we use it for Parcelable
 * impersonation
 */
/**
 * you can convert your json to kotlin data class in easy simple way, by using
 * @(JSON To Kotlin Class) plugin in android studio, you can install the plugin as any other
 * plugin and then use it, for more details check here:
 * https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
 */
@Parcelize
data class Multimedia(@SerializedName("url") var url: String? = null,
                      @SerializedName("format") var format: String? = null,
                      @SerializedName("height") var height: Long? = null,
                      @SerializedName("width") var width: Long? = null,
                      @SerializedName("type") var type: String? = null,
                      @SerializedName("subtype") var subtype: String? = null,
                      @SerializedName("caption") var caption: String? = null,
                      @SerializedName("copyright") var copyright: String? = null) : Parcelable

