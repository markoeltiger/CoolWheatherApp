package com.example.coolwheatherapp.repository

import com.example.coolwheatherapp.data.model.Weather.CurrentWeather
import com.example.coolwheatherapp.data.network.WeatherApiService
import com.example.coolwheatherapp.util.Constatnts
import javax.inject.Inject

class WeatherRepo  @Inject constructor(
    private val apiService:WeatherApiService
){
    suspend fun gerCurrentWeather(): CurrentWeather {
        return apiService.getCurrentWeather(Constatnts.API_KEY,"Alexandria")
    }
}
