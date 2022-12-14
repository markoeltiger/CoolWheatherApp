package com.example.coolwheatherapp.data.network

import com.example.coolwheatherapp.data.model.CurrentWeather
import com.example.coolwheatherapp.util.Constatnts
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
@GET(Constatnts.CURRENT_ENDPOINT)
suspend fun getCurrentWeather(
    @Query("key") key: String?,

    @Query("q") city: String?,

    ):CurrentWeather

}