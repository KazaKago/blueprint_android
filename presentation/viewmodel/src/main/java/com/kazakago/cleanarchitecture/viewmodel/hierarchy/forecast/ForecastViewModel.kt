package com.kazakago.cleanarchitecture.viewmodel.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.weather.Forecast
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.usecase.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
import kotlinx.coroutines.launch

class ForecastViewModel(
    application: Application,
    private val getWeatherUseCase: GetWeatherUseCase,
    city: City
) : AndroidViewModel(application) {

    private val _city = NullSafeLiveData(city)
    val city: NullSafeLiveData<City> get() = _city
    private val _weather = MutableNullSafeLiveData<Weather>()
    val weather: NullSafeLiveData<Weather> get() = _weather
    private val _showForecast = MutableLiveEvent<Forecast>()
    val showForecast: LiveEvent<Forecast> get() = _showForecast
    private val _isLoading = MutableNullSafeLiveData(false)
    val isLoading: NullSafeLiveData<Boolean> get() = _isLoading
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
