package com.example.coolwheatherapp.data.model.Weather


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentX(
    @Json(name = "cloud")
    val cloud: Int,
    @Json(name = "condition")
    val condition: ConditionX,
    @Json(name = "feelslike_c")
    val feelslikeC: Double,
    @Json(name = "feelslike_f")
    val feelslikeF: Double,
    @Json(name = "gust_kph")
    val gustKph: Double,
    @Json(name = "gust_mph")
    val gustMph: Double,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "is_day")
    val isDay: Int,
    @Json(name = "last_updated")
    val lastUpdated: String,
    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Int,
    @Json(name = "precip_in")
    val precipIn: Double,
    @Json(name = "precip_mm")
    val precipMm: Double,
    @Json(name = "pressure_in")
    val pressureIn: Double,
    @Json(name = "pressure_mb")
    val pressureMb: Double,
    @Json(name = "temp_c")
    val tempC: Double,
    @Json(name = "temp_f")
    val tempF: Double,
    @Json(name = "uv")
    val uv: Double,
    @Json(name = "vis_km")
    val visKm: Double,
    @Json(name = "vis_miles")
    val visMiles: Double,
    @Json(name = "wind_degree")
    val windDegree: Int,
    @Json(name = "wind_dir")
    val windDir: String,
    @Json(name = "wind_kph")
    val windKph: Double,
    @Json(name = "wind_mph")
    val windMph: Double
)