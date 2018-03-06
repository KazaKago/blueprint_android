package com.kazakago.cleanarchitecture.presentation.presenter.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.LazyKodeinAware
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.presentation.extension.compositeLocalizedMessage
import com.kazakago.cleanarchitecture.presentation.listener.fragment.ForecastFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.view.adapter.ForecastRecyclerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ForecastFragmentViewModel(application: Application, private val city: City) : AndroidViewModel(application), LazyKodeinAware, ForecastRecyclerAdapter.Listener {

    class Factory(private val application: Application, private val city: City): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ForecastFragmentViewModel(application, city) as T
        }
    }

    override val kodein = LazyKodein(application.appKodein)

    val weather = MutableLiveData<Weather>()
    val isLoading = MutableLiveData<Boolean>()

    var listener: ForecastFragmentViewModelListener? = null
    private val compositeDisposable = CompositeDisposable()
    private val getWeatherUseCase: GetWeatherUseCase by instance()

    init {
        isLoading.value = false
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
                            listener?.showToast(it.compositeLocalizedMessage())
                        }
                ))
    }

    /* ForecastRecyclerAdapter.Listener */

    override fun onItemClick(forecast: Forecast) {
        listener?.showToast(forecast.telop)
    }

}
