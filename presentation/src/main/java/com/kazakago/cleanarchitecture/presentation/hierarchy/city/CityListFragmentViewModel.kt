package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.SingleLiveEvent
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CityListFragmentViewModel(application: Application,
                                private val getCityUseCase: GetCityUseCase) : AndroidViewModel(application), CityRecyclerAdapter.Listener {

    val cityList = MutableLiveData<List<City>>()
    val showToast = SingleLiveEvent<String>()
    val toForecast = SingleLiveEvent<City>()

    init {
        fetchCityList()
    }

    private fun fetchCityList() = launch(UI) {
        try {
            cityList.value = async { getCityUseCase.execute(Unit) }.await()
        } catch (exception: Exception) {
            cityList.value = listOf()
            showToast.call(exception.localizedMessage)
        }
    }

    //region CityRecyclerAdapter.Listener

    override fun onItemClick(city: City) {
        toForecast.call(city)
    }

    //endregion

}
