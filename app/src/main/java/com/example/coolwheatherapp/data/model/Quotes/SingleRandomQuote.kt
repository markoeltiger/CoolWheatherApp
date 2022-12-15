package com.example.coolwheatherapp.data.model.Quotes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleRandomQuote(
    @Json(name = "author")
    val author: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "quote")
    val quote: String
)