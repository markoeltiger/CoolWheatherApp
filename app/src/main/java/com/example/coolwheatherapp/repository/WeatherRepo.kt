package com.example.coolwheatherapp.repository

import com.example.coolwheatherapp.data.model.CurrentWeather
import com.example.coolwheatherapp.data.network.ApiService
import javax.inject.Inject

class WeatherRepo  @Inject constructor(
    private val apiService:ApiService
){
    suspend fun gerCurrentWeather():CurrentWeather{
        return apiService.getCurrentWeather()
    }
}