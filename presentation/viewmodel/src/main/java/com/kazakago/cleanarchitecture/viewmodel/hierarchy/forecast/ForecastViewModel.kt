package com.kazakago.cleanarchitecture.viewmodel.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.usecase.output.weather.WeatherOutput
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
    cityId: CityId
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
        subscribeWeather(cityId)
    }

    private fun subscribeWeather(cityId: CityId) = viewModelScope.launch {
        subscribeWeatherUseCase(cityId).collect {
            updateWeatherState(it)
            updateWeatherValue(it.value)
        }
    }

    private fun updateWeatherState(state: StoreState<WeatherOutput>) {
        when (state) {
            is StoreState.Fixed -> {
                _isLoading.value = false
            }
            is StoreState.Loading -> {
                _isLoading.value = true
            }
            is StoreState.Error -> {
                _isLoading.value = false
                _showError.call(state.exception)
            }
        }
    }

    private fun updateWeatherValue(value: StoreValue<WeatherOutput>) {
        when (value) {
            is StoreValue.Stored -> {
                _weather.value = value.value.weather
                _city.value = value.value.city
            }
            is StoreValue.NotStored -> {
                //do nothing.
            }
        }
    }

}
