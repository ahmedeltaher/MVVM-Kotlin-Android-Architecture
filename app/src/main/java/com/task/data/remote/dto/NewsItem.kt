package com.task.data.remote.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
        @SerializedName("abstract")
        var abstractInfo: String = "",
        var byline: String = "",
        @SerializedName("created_date")
        var createdDate: String = "",
        @SerializedName("des_facet")
        var desFacet: List<String> = listOf(),
        @SerializedName("geo_facet")
        var geoFacet: List<String> = listOf(),
        @SerializedName("item_type")
        var itemType: String = "",
        var kicker: String = "",
        @SerializedName("material_type_facet")
        var materialTypeFacet: String = "",
        var multimedia: List<Multimedia> = listOf(),
        @SerializedName("org_facet")
        var orgFacet: List<String> = listOf(),
        @SerializedName("per_facet")
        var perFacet: List<String> = listOf(),
        @SerializedName("published_date")
        var publishedDate: String = "",
        var section: String = "",
        @SerializedName("short_url")
        var shortUrl: String = "",
        var subsection: String = "",
        var title: String = "",
        @SerializedName("updated_date")
        var updatedDate: String = "",
        var url: String = ""
) : Parcelable