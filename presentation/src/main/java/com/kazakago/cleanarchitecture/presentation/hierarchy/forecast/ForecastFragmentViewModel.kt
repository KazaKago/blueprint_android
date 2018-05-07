package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.presentation.extension.compositeLocalizedMessage
import com.kazakago.cleanarchitecture.presentation.livedata.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ForecastFragmentViewModel(application: Application,
                                private val getWeatherUseCase: GetWeatherUseCase,
                                private val city: City) : AndroidViewModel(application), ForecastRecyclerAdapter.Listener {

    val weather = MutableLiveData<Weather>()
    val isLoading = MutableLiveData<Boolean>()
    val showToast = SingleLiveEvent<String>()

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchWeather()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun fetchWeather() {
        compositeDisposable.add(getWeatherUseCase.execute(city.id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    isLoading.value = true
                }
                .doFinally {
                    isLoading.value = false
                }
                .subscribeBy(
                        onSuccess = {
                            weather.value = it
                        },
                        onError = {
                            weather.value = null
                            showToast.call(it.compositeLocalizedMessage())
                        }
                ))
    }

    //region ForecastRecyclerAdapter.Listener

    override fun onItemClick(forecast: Forecast) {
        showToast.call(forecast.telop)
    }

    //endregion

}
