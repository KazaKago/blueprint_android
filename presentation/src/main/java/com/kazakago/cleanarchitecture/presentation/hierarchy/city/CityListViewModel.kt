package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.NonNullLiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.NonNullMutableLiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullMutableLiveData
import kotlinx.coroutines.launch

class CityListViewModel(
    application: Application,
    private val getCityUseCase: GetCityUseCase
) : AndroidViewModel(application) {

    private val _cityList = NonNullMutableLiveData<List<City>>(emptyList())
    val cityList: NonNullLiveData<List<City>> get() = _cityList
    private val _showToast = NonNullMutableLiveEvent<String>()
    val showToast: NonNullLiveEvent<String> get() = _showToast

    init {
        fetchCityList()
    }

    private fun fetchCityList() = viewModelScope.launch {
        try {
            _cityList.value = getCityUseCase(Unit)
        } catch (exception: Exception) {
            _cityList.value = listOf()
            _showToast.call(exception.localizedMessage)
        }
    }

}
