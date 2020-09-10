package com.task.data.dto.recipes


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class RecipesItem(
        @Json(name = "calories")
        val calories: String = "",
        @Json(name = "carbos")
        val carbos: String = "",
        @Json(name = "card")
        val card: String = "",
        @Json(name = "country")
        val country: String = "",
        @Json(name = "deliverable_ingredients")
        val deliverableIngredients: List<String> = listOf(),
        @Json(name = "description")
        val description: String = "",
        @Json(name = "difficulty")
        val difficulty: Int = 0,
        @Json(name = "fats")
        val fats: String = "",
        @Json(name = "favorites")
        val favorites: Int = 0,
        @Json(name = "fibers")
        val fibers: String = "",
        @Json(name = "headline")
        val headline: String = "",
        @Json(name = "highlighted")
        val highlighted: Boolean = false,
        @Json(name = "id")
        val id: String = "",
        @Json(name = "image")
        val image: String = "",
        @Json(name = "incompatibilities")
        val incompatibilities: String = "",
        @Json(name = "ingredients")
        val ingredients: List<String> = listOf(),
        @Json(name = "keywords")
        val keywords: List<String> = listOf(),
        @Json(name = "name")
        val name: String = "",
        @Json(name = "products")
        val products: List<String> = listOf(),
        @Json(name = "proteins")
        val proteins: String = "",
        @Json(name = "rating")
        val rating: Double = 0.0,
        @Json(name = "ratings")
        val ratings: Int = 0,
        @Json(name = "thumb")
        val thumb: String = "",
        @Json(name = "time")
        val time: String = "",
        @Json(name = "undeliverable_ingredients")
        val undeliverableIngredients: List<String> = listOf(),
        @Json(name = "user")
        val user: User = User(),
        @Json(name = "weeks")
        val weeks: List<String> = listOf()
) : Parcelable
