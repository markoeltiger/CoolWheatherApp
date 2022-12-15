package com.example.coolwheatherapp.util

import com.example.coolwheatherapp.data.model.Weather.CurrentWeather

sealed class WeatherUiState {
    object Empty : WeatherUiState()
    object Loading : WeatherUiState()
    class Loaded(val data: CurrentWeather) : WeatherUiState()
    class Error(val message: String) : WeatherUiState()
}