package com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.model.state.StateContent
import com.kazakago.cleanarchitecture.domain.usecase.usecase.city.SubscribeCityListUseCase
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.liveevent.LiveEvent
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.liveevent.MutableLiveEvent
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
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
            updateCityListContent(it.content)
        }
    }

    private fun updateCityListState(state: State<List<City>>) {
        when (state) {
            is State.Fixed -> {
                _isLoading.value = false
            }
            is State.Loading -> {
                _isLoading.value = true
            }
            is State.Error -> {
                _isLoading.value = false
                _showError.call(state.exception)
            }
        }
    }

    private fun updateCityListContent(content: StateContent<List<City>>) {
        when (content) {
            is StateContent.Stored -> {
                _cityList.value = content.rawContent
            }
            is StateContent.NotStored -> {
                //do nothing.
            }
        }
    }

}
