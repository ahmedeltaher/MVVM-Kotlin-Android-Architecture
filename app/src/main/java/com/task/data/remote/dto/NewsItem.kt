package com.task.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
                    @SerializedName("multimedia") var multimedia: List<Multimedium>? = null,
                    @SerializedName("short_url") var shortUrl: String? = null) : Parcelable
