package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.livedata.liveevent.NonNullLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CityListViewModel(
    application: Application,
    private val getCityUseCase: GetCityUseCase
) : AndroidViewModel(application) {

    val goForecast = NonNullLiveEvent<City>()
    val cityList = NonNullLiveData<List<City>>(emptyList())
    val showToast = NonNullLiveEvent<String>()

    init {
        fetchCityList()
    }

    private fun fetchCityList() = GlobalScope.launch(Dispatchers.Main) {
        try {
            cityList.value = getCityUseCase.execute(Unit)
        } catch (exception: Exception) {
            cityList.value = listOf()
            showToast.call(exception.localizedMessage)
        }
    }

}
