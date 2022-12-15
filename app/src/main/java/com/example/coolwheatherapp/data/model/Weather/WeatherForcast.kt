package com.example.coolwheatherapp.data.model.Weather


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForcast(
    @Json(name = "alerts")
    val alerts: Alerts,
    @Json(name = "current")
    val current: CurrentX,
    @Json(name = "forecast")
    val forecast: Forecast,
    @Json(name = "location")
    val location: LocationX
)