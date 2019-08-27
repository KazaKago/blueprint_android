package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullMutableLiveData
import kotlinx.coroutines.launch

class CityListViewModel(
    application: Application,
    private val getCityUseCase: GetCityUseCase
) : AndroidViewModel(application) {

    private val _cityList = NonNullMutableLiveData<List<City>>(emptyList())
    val cityList: NonNullLiveData<List<City>> get() = _cityList
    private val _showError = MutableLiveEvent<Exception>()
    val showError: LiveEvent<Exception> get() = _showError

    init {
        fetchCityList()
    }

    private fun fetchCityList() = viewModelScope.launch {
        try {
            _cityList.value = getCityUseCase()
        } catch (exception: Exception) {
            _cityList.value = listOf()
            _showError.call(exception)
        }
    }

}
