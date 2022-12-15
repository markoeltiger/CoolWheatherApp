package com.example.coolwheatherapp.data.model.Weather


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationX(
    @Json(name = "country")
    val country: String,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "localtime")
    val localtime: String,
    @Json(name = "localtime_epoch")
    val localtimeEpoch: Int,
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "name")
    val name: String,
    @Json(name = "region")
    val region: String,
    @Json(name = "tz_id")
    val tzId: String
)