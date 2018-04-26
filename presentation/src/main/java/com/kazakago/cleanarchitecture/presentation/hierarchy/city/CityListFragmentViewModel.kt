package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.extension.compositeLocalizedMessage
import com.kazakago.cleanarchitecture.presentation.livedata.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CityListFragmentViewModel(application: Application,
                                private val getCityUseCase: GetCityUseCase) : AndroidViewModel(application), CityRecyclerAdapter.Listener {

    val cityList = MutableLiveData<List<City>>()
    val showToast = SingleLiveEvent<String>()
    val toForecast = SingleLiveEvent<City>()

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchCityList()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun fetchCityList() {
        compositeDisposable.add(getCityUseCase.execute(Unit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            cityList.value = it
                        },
                        onError = {
                            cityList.value = listOf()
                            showToast.call(it.compositeLocalizedMessage())
                        }
                ))
    }

    //region CityRecyclerAdapter.Listener

    override fun onItemClick(city: City) {
        toForecast.call(city)
    }

    //endregion

}
