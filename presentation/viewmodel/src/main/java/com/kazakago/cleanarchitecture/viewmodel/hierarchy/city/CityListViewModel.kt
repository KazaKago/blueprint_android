package com.kazakago.cleanarchitecture.viewmodel.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import com.kazakago.cleanarchitecture.usecase.usecase.city.SubscribeCityListUseCase
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CityListViewModel(
    application: Application,
    private val subscribeCityListUseCase: SubscribeCityListUseCase
) : AndroidViewModel(application) {

    private val _cityList = MutableNullSafeLiveData<List<City>>()
    val cityList: NullSafeLiveData<List<City>> get() = _cityList
    private val _isLoading = MutableNullSafeLiveData(false)
    val isLoading: NullSafeLiveData<Boolean> get() = _isLoading
    private val _showError = MutableLiveEvent<Exception>()
    val showError: LiveEvent<Exception> get() = _showError

    init {
        subscribeCityList()
    }

    private fun subscribeCityList() = viewModelScope.launch {
        subscribeCityListUseCase().collect {
            updateCityListState(it)
            updateCityListValue(it.value)
        }
    }

    private fun updateCityListState(state: StoreState<List<City>>) {
        when (state) {
            is StoreState.Fixed -> {
                _isLoading.value = false
            }
            is StoreState.Loading -> {
                _isLoading.value = true
            }
            is StoreState.Error -> {
                _isLoading.value = false
                _showError.call(state.exception)
            }
        }
    }

    private fun updateCityListValue(value: StoreValue<List<City>>) {
        when (value) {
            is StoreValue.Stored -> {
                _cityList.value = value.value
            }
            is StoreValue.NotStored -> {
                //do nothing.
            }
        }
    }

}
