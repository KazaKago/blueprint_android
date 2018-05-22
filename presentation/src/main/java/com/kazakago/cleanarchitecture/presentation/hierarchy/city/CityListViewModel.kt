package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.LiveEvent
import com.kazakago.cleanarchitecture.presentation.livedata.UnitLiveEvent
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CityListViewModel(application: Application,
                        private val getCityUseCase: GetCityUseCase) : AndroidViewModel(application), CityRecyclerAdapter.Listener {

    val toAbout = UnitLiveEvent()
    val cityList = MutableLiveData<List<City>>()
    val showToast = LiveEvent<String>()
    val toForecast = LiveEvent<City>()

    init {
        fetchCityList()
    }

    fun onClickAboutMenu() {
        toAbout.call()
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
