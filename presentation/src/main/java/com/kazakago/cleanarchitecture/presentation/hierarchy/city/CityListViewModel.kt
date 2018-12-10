package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.liveevent.NonNullLiveEvent
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.viewmodel.CoroutineAndroidViewModel
import kotlinx.coroutines.launch

class CityListViewModel(
    application: Application,
    private val getCityUseCase: GetCityUseCase
) : CoroutineAndroidViewModel(application) {

    val cityList = NonNullLiveData<List<City>>(emptyList())
    val showToast = NonNullLiveEvent<String>()

    init {
        fetchCityList()
    }

    private fun fetchCityList() = launch {
        try {
            cityList.value = getCityUseCase.execute(Unit)
        } catch (exception: Exception) {
            cityList.value = listOf()
            showToast.call(exception.localizedMessage)
        }
    }

}
