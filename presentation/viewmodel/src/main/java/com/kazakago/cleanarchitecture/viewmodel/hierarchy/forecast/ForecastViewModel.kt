package com.kazakago.cleanarchitecture.viewmodel.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.usecase.usecase.weather.SubscribeWeatherUseCase
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ForecastViewModel(
    application: Application,
    private val subscribeWeatherUseCase: SubscribeWeatherUseCase,
    city: City
) : AndroidViewModel(application) {

    private val _city = NullSafeLiveData(city)
    val city: NullSafeLiveData<City> get() = _city
    private val _weather = MutableNullSafeLiveData<StoreState<Weather>>()
    val weather: NullSafeLiveData<StoreState<Weather>> get() = _weather

    init {
        subscribeWeather(city.id)
    }

    private fun subscribeWeather(cityId: CityId) = viewModelScope.launch {
        subscribeWeatherUseCase(cityId).collect {
            _weather.value = it
        }
    }

}
