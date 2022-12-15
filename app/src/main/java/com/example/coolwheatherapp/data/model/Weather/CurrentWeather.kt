package com.example.coolwheatherapp.data.model.Weather


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeather(
    @Json(name = "current")
    val current: Current,
    @Json(name = "location")
    val location: Location
)