package com.kazakago.cleanarchitecture.viewmodel.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.usecase.usecase.city.SubscribeCityUseCase
import com.kazakago.cleanarchitecture.usecase.usecase.weather.SubscribeWeatherUseCase
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ForecastViewModel(
    application: Application,
    private val subscribeCityUseCase: SubscribeCityUseCase,
    private val subscribeWeatherUseCase: SubscribeWeatherUseCase,
    cityId: CityId
) : AndroidViewModel(application) {

    private val _city = MutableNullSafeLiveData<StoreState<City>>()
    val city: NullSafeLiveData<StoreState<City>> get() = _city
    private val _weather = MutableNullSafeLiveData<StoreState<Weather>>()
    val weather: NullSafeLiveData<StoreState<Weather>> get() = _weather

    init {
        subscribeCity(cityId)
        subscribeWeather(cityId)
    }

    private fun subscribeCity(cityId: CityId) = viewModelScope.launch {
        subscribeCityUseCase(cityId).collect {
            _city.value = it
        }
    }

    private fun subscribeWeather(cityId: CityId) = viewModelScope.launch {
        subscribeWeatherUseCase(cityId).collect {
            _weather.value = it
        }
    }

}
