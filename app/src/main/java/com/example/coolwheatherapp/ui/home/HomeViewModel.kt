package com.example.coolwheatherapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coolwheatherapp.data.model.Quotes.SingleRandomQuote
import com.example.coolwheatherapp.data.model.Weather.CurrentWeather
import com.example.coolwheatherapp.data.model.Weather.WeatherForcast
import com.example.coolwheatherapp.repository.QuoteRepo
import com.example.coolwheatherapp.repository.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo,
    private val quoteRepo: QuoteRepo

):ViewModel(){

private val _resp=MutableLiveData<CurrentWeather>()
    val weatherResp:LiveData<CurrentWeather>
    get()= _resp
    private val _forcastresp=MutableLiveData<WeatherForcast>()
    val forcastweatherResp:LiveData<WeatherForcast>
        get()= _forcastresp
    private val _respRandom=MutableLiveData<SingleRandomQuote>()
    val quoteResp:LiveData<SingleRandomQuote>
        get()= _respRandom

    init {
        getWeather()
        getQuote()
     }
    private fun getQuote() =viewModelScope.launch{
        quoteRepo.getRandomQuote().let {
            _respRandom.postValue(it)
        }
    }
    private fun getWeather() =viewModelScope.launch{
        weatherRepo.gerCurrentWeather().let {
            _resp.postValue(it)
        weatherRepo.getWeatherForcast().let {
            _forcastresp.postValue(it)
        }

        }
       }
    }

