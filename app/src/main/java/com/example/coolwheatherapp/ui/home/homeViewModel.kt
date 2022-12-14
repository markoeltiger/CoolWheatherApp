package com.example.coolwheatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coolwheatherapp.data.model.CurrentWeather
import com.example.coolwheatherapp.repository.WeatherRepo
import com.example.coolwheatherapp.util.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.migration.CustomInjection.inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class homeViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo

):ViewModel(){

private val _resp=MutableLiveData<CurrentWeather>()
    val weatherResp:LiveData<CurrentWeather>
    get()= _resp

    init {
        getWeather()

    }

    private fun getWeather() =viewModelScope.launch{
        weatherRepo.gerCurrentWeather().let {
            _resp.postValue(it)
        }
       }
    }

