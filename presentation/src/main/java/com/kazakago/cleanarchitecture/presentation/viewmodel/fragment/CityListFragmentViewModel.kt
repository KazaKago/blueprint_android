package com.kazakago.cleanarchitecture.presentation.viewmodel.fragment

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
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.extension.compositeLocalizedMessage
import com.kazakago.cleanarchitecture.presentation.listener.fragment.CityListFragmentViewModelListener
import com.kazakago.cleanarchitecture.presentation.view.adapter.CityRecyclerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CityListFragmentViewModel(application: Application) : AndroidViewModel(application), LazyKodeinAware, CityRecyclerAdapter.Listener {

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CityListFragmentViewModel(application) as T
        }
    }

    override val kodein = LazyKodein(application.appKodein)

    val cityList = MutableLiveData<List<City>>()

    var listener: CityListFragmentViewModelListener? = null
    private val compositeDisposable = CompositeDisposable()
    private val getCityUseCase: GetCityUseCase by instance()

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
                            listener?.showToast(it.compositeLocalizedMessage())
                        }
                ))
    }

    /* CityRecyclerAdapter.Listener */

    override fun onItemClick(city: City) {
        listener?.toForecastActivity(city)
    }

}
