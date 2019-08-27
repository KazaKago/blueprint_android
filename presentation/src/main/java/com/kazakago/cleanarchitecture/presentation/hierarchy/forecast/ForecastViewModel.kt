package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.LateInitLiveData
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.LateInitMutableLiveData
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullMutableLiveData
import kotlinx.coroutines.launch

class ForecastViewModel(
    application: Application,
    private val getWeatherUseCase: GetWeatherUseCase,
    city: City
) : AndroidViewModel(application) {

    val city = NonNullLiveData(city)
    private val _weather = LateInitMutableLiveData<Weather>()
    val weather: LateInitLiveData<Weather> get() = _weather
    private val _showForecast = MutableLiveEvent<Forecast>()
    val showForecast: LiveEvent<Forecast> get() = _showForecast
    private val _isLoading = NonNullMutableLiveData(false)
    val isLoading: NonNullLiveData<Boolean> get() = _isLoading
    private val _showError = MutableLiveEvent<Exception>()
    val showError: LiveEvent<Exception> get() = _showError

    init {
        fetchWeather()
    }

    fun onClickForecast(forecast: Forecast) {
        _showForecast.call(forecast)
    }

    private fun fetchWeather() = viewModelScope.launch {
        _isLoading.value = true
        try {
            _weather.value = getWeatherUseCase(city.value.id)
        } catch (exception: Exception) {
            _showError.call(exception)
        }
        _isLoading.value = false
    }

}
