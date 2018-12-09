package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.liveevent.NonNullLiveEvent
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ForecastViewModel(
    application: Application,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val city: City
) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val title = MutableLiveData<CharSequence>()
    val weather = MutableLiveData<Weather>()
    val isLoading = NonNullLiveData(false)
    val showToast = NonNullLiveEvent<String>()

    init {
        title.value = city.name
        fetchWeather()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun onClickForecast(forecast: Forecast) {
        showToast.call(forecast.telop)
    }

    private fun fetchWeather() = launch(context = coroutineContext) {
        isLoading.value = true
        try {
            weather.value = getWeatherUseCase.execute(city.id)
        } catch (exception: Exception) {
            weather.value = null
            showToast.call(exception.localizedMessage)
        }
        isLoading.value = false
    }

}
