package com.kazakago.cleanarchitecture.viewmodel.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.State
import com.kazakago.cleanarchitecture.model.state.StateContent
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.usecase.output.weather.WeatherOutput
import com.kazakago.cleanarchitecture.usecase.usecase.weather.RequestWeatherUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.weather.SubscribeWeatherUseCase
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ForecastViewModel(
    application: Application,
    private val subscribeWeatherUseCase: SubscribeWeatherUseCase,
    private val requestWeatherUseCase: RequestWeatherUseCase,
    private val cityId: CityId
) : AndroidViewModel(application) {

    private val _city = MutableNullSafeLiveData<City>()
    val city: NullSafeLiveData<City> get() = _city
    private val _weather = MutableNullSafeLiveData<Weather>()
    val weather: NullSafeLiveData<Weather> get() = _weather
    private val _isLoading = MutableNullSafeLiveData(false)
    val isLoading: NullSafeLiveData<Boolean> get() = _isLoading
    private val _showError = MutableLiveEvent<Exception>()
    val showError: LiveEvent<Exception> get() = _showError

    init {
        subscribeWeather()
    }

    fun requestWeather() = viewModelScope.launch {
        requestWeatherUseCase(cityId)
    }

    private fun subscribeWeather() = viewModelScope.launch {
        subscribeWeatherUseCase(cityId).collect {
            updateWeatherState(it)
            updateWeatherContent(it.content)
        }
    }

    private fun updateWeatherState(state: State<WeatherOutput>) {
        when (state) {
            is State.Fixed -> {
                _isLoading.value = false
            }
            is State.Loading -> {
                _isLoading.value = true
            }
            is State.Error -> {
                _isLoading.value = false
                _showError.call(state.exception)
            }
        }
    }

    private fun updateWeatherContent(content: StateContent<WeatherOutput>) {
        when (content) {
            is StateContent.Stored -> {
                _weather.value = content.rawContent.weather
                _city.value = content.rawContent.city
            }
            is StateContent.NotStored -> {
                //do nothing.
            }
        }
    }

}
