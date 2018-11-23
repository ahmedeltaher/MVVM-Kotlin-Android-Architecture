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
data class NewsItem(@SerializedName("section") var section: String? = null,
                    @SerializedName("subsection") var subsection: String? = null,
                    @SerializedName("title") var title: String? = null,
                    @SerializedName("abstract") var abstract: String? = null,
                    @SerializedName("url") var url: String? = null,
                    @SerializedName("byline") var byline: String? = null,
                    @SerializedName("item_type") var itemType: String? = null,
                    @SerializedName("updated_date") var updatedDate: String? = null,
                    @SerializedName("created_date") var createdDate: String? = null,
                    @SerializedName("published_date") var publishedDate: String? = null,
                    @SerializedName("material_type_facet") var materialTypeFacet: String? = null,
                    @SerializedName("kicker") var kicker: String? = null,
                    @SerializedName("des_facet") var desFacet: List<String>? = null,
                    @SerializedName("org_facet") var orgFacet: List<String>? = null,
                    @SerializedName("per_facet") var perFacet: List<String>? = null,
                    @SerializedName("geo_facet") var geoFacet: List<String>? = null,
                    @SerializedName("multimedia") var multimedia: List<Multimedia>? = null,
                    @SerializedName("short_url") var shortUrl: String? = null) : Parcelable
