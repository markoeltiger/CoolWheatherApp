package com.example.coolwheatherapp.data.model.Weather


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Alerts(
    @Json(name = "alert")
    val alert: List<Any>
)