package com.example.coolwheatherapp.repository

import com.example.coolwheatherapp.data.model.CurrentWeather
import com.example.coolwheatherapp.data.network.ApiService
import com.example.coolwheatherapp.util.Constatnts
import javax.inject.Inject

class WeatherRepo  @Inject constructor(
    private val apiService:ApiService
){
    suspend fun gerCurrentWeather():CurrentWeather{
        return apiService.getCurrentWeather(Constatnts.API_KEY,"Alexandria")
    }
}