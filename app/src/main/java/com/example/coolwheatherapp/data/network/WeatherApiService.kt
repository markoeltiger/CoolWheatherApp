package com.example.coolwheatherapp.data.network

import com.example.coolwheatherapp.data.model.Weather.CurrentWeather
import com.example.coolwheatherapp.util.Constatnts
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
@GET(Constatnts.CURRENT_ENDPOINT)
suspend fun getCurrentWeather(
    @Query("key") key: String?,
    @Query("q") city: String?,
    ): CurrentWeather

}