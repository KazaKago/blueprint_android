package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.NonNullLiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.LateInitLiveData
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullLiveData
import kotlinx.coroutines.launch

class ForecastViewModel(
    application: Application,
    private val getWeatherUseCase: GetWeatherUseCase,
    city: City
) : AndroidViewModel(application) {

    val city = NonNullLiveData(city)
    val weather = LateInitLiveData<Weather>()
    val isLoading = NonNullLiveData(false)
    val showToast = NonNullLiveEvent<String>()

    init {
        fetchWeather()
    }

    fun onClickForecast(forecast: Forecast) {
        showToast.call(forecast.telop)
    }

    private fun fetchWeather() = viewModelScope.launch {
        isLoading.value = true
        try {
            weather.value = getWeatherUseCase.execute(city.value.id)
        } catch (exception: Exception) {
            showToast.call(exception.localizedMessage)
        }
        isLoading.value = false
    }

}
