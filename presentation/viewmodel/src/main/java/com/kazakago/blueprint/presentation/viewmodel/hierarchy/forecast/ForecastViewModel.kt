package com.kazakago.blueprint.presentation.viewmodel.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.blueprint.domain.model.global.state.State
import com.kazakago.blueprint.domain.model.global.state.StateContent
import com.kazakago.blueprint.domain.model.hierarchy.city.City
import com.kazakago.blueprint.domain.model.hierarchy.city.CityId
import com.kazakago.blueprint.domain.model.hierarchy.weather.Weather
import com.kazakago.blueprint.domain.usecase.hierarchy.weather.RequestWeatherUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.weather.SubscribeWeatherUseCase
import com.kazakago.blueprint.domain.usecase.output.weather.WeatherOutput
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ForecastViewModel(
    application: Application,
    private val subscribeWeatherUseCase: SubscribeWeatherUseCase,
    private val requestWeatherUseCase: RequestWeatherUseCase,
    private val cityId: CityId
) : AndroidViewModel(application) {

    private val _city = MutableStateFlow<City?>(null)
    val city: StateFlow<City?> get() = _city
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> get() = _weather
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    private val _showError = MutableSharedFlow<Exception>()
    val showError: SharedFlow<Exception> get() = _showError

    init {
        subscribeWeather()
    }

    fun requestWeather() {
        viewModelScope.launch {
            requestWeatherUseCase(cityId)
        }
    }

    private fun subscribeWeather() {
        viewModelScope.launch {
            subscribeWeatherUseCase(cityId).collect {
                updateWeatherState(it)
                updateWeatherContent(it.content)
            }
        }
    }

    private suspend fun updateWeatherState(state: State<WeatherOutput>) {
        when (state) {
            is State.Fixed -> {
                _isLoading.value = false
            }
            is State.Loading -> {
                _isLoading.value = true
            }
            is State.Error -> {
                _isLoading.value = false
                _showError.emit(state.exception)
            }
        }
    }

    private fun updateWeatherContent(content: StateContent<WeatherOutput>) {
        when (content) {
            is StateContent.Exist -> {
                _weather.value = content.rawContent.weather
                _city.value = content.rawContent.city
            }
            is StateContent.NotExist -> {
                //do nothing.
            }
        }
    }
}
