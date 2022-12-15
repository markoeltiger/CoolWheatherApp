package com.example.coolwheatherapp.data.network

import com.example.coolwheatherapp.data.model.Weather.CurrentWeather
import com.example.coolwheatherapp.data.model.Weather.WeatherForcast
import com.example.coolwheatherapp.util.Constatnts
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
@GET(Constatnts.CURRENT_ENDPOINT)
suspend fun getCurrentWeather(
    @Query("key") key: String?,
    @Query("q") city: String?,
    ): CurrentWeather
    @GET(Constatnts.FORCAST_ENDPOINT)
    suspend fun getWeatherForcast(
        @Query("key") key: String?,
        @Query("q") city: String?,
        @Query("days") days: Int?,

    @Query("alerts") alerts: String?,

        ): WeatherForcast
}