package com.example.coolwheatherapp.data.network

import com.example.coolwheatherapp.data.model.CurrentWeather
import com.example.coolwheatherapp.util.Constatnts
import retrofit2.http.GET

interface ApiService {
@GET(Constatnts.API_BASE_URL)
suspend fun getCurrentWeather():CurrentWeather

}