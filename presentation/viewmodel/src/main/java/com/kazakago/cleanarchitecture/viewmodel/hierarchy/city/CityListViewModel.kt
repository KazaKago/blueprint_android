package com.kazakago.cleanarchitecture.viewmodel.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.usecase.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
import kotlinx.coroutines.launch

class CityListViewModel(
    application: Application,
    private val getCityUseCase: GetCityUseCase
) : AndroidViewModel(application) {

    private val _cityList = MutableNullSafeLiveData<List<City>>(emptyList())
    val cityList: NullSafeLiveData<List<City>> get() = _cityList
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
