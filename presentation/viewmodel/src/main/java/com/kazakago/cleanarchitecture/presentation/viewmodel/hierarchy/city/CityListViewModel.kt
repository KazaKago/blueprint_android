package com.kazakago.cleanarchitecture.presentation.viewmodel.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.global.state.StateContent
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.domain.usecase.hierarchy.city.SubscribeCityListUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CityListViewModel(
    application: Application,
    private val subscribeCityListUseCase: SubscribeCityListUseCase
) : AndroidViewModel(application) {

    private val _cityList = MutableStateFlow<List<City>>(emptyList())
    val cityList: StateFlow<List<City>> get() = _cityList
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    private val _showError = MutableSharedFlow<Exception>()
    val showError: SharedFlow<Exception> get() = _showError

    init {
        subscribeCityList()
    }

    private fun subscribeCityList() = viewModelScope.launch {
        subscribeCityListUseCase().collect {
            updateCityListState(it)
            updateCityListContent(it.content)
        }
    }

    private suspend fun updateCityListState(state: State<List<City>>) {
        when (state) {
            is State.Fixed -> {
                _isLoading.value = false
            }
            is State.Loading -> {
                _isLoading.value = true
            }
            is State.Error -> {
                _isLoading.value = false
                _showError.emit(state.exception)
            }
        }
    }

    private fun updateCityListContent(content: StateContent<List<City>>) {
        when (content) {
            is StateContent.Exist -> {
                _cityList.value = content.rawContent
            }
            is StateContent.NotExist -> {
                //do nothing.
            }
        }
    }

}
