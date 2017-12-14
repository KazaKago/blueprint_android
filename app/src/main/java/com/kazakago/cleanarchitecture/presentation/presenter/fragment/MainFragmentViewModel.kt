package com.kazakago.cleanarchitecture.presentation.presenter.fragment

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.LazyKodeinAware
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import com.kazakago.cleanarchitecture.domain.model.weather.ForecastModel
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import com.kazakago.cleanarchitecture.presentation.listener.presenter.fragment.MainFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.view.adapter.ForecastRecyclerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainFragmentViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver, LazyKodeinAware, ForecastRecyclerAdapter.Listener {

    override val kodein = LazyKodein(application.appKodein)

    val cityList = MutableLiveData<List<CityModel>>()
    val weather = MutableLiveData<WeatherModel>()
    val selectedPosition = MutableLiveData<Int>()

    var listener: MainFragmentViewModelListener? = null
    private val compositeDisposable = CompositeDisposable()
    private val getWeatherUseCase: GetWeatherUseCase by instance()
    private val getCityUseCase: GetCityUseCase by instance()

    init {
        fetchCityList {
            fetchWeather()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun onClickRefresh() {
        fetchWeather()
    }

    fun onCitySelected(position: Int) {
        selectedPosition.value = position
        fetchWeather()
    }

    private fun fetchCityList(completion: (() -> Unit)? = null) {
        compositeDisposable.add(getCityUseCase.execute(Unit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribeBy(
                        onSuccess = {
                            cityList.value = it
                            completion?.invoke()
                        },
                        onError = {
                            listener?.showToast(it.localizedMessage)
                            completion?.invoke()
                        }
                ))
    }

    private fun fetchWeather(completion: (() -> Unit)? = null) {
        cityList.value?.get(selectedPosition.value ?: 0)?.id?.let {
            compositeDisposable.add(getWeatherUseCase.execute(it)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {
                                weather.value = it
                                completion?.invoke()
                            },
                            onError = {
                                weather.value = null
                                listener?.showToast(it.localizedMessage)
                                completion?.invoke()
                            }
                    ))
        }
    }

    /* ForecastRecyclerAdapter.Listener */

    override fun onItemClick(forecastModel: ForecastModel) {
        listener?.showToast(forecastModel.telop)
    }

}
