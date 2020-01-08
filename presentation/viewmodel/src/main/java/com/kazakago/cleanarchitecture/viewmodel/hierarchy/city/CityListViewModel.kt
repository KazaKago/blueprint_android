package com.kazakago.cleanarchitecture.viewmodel.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.usecase.usecase.city.SubscribeCityListUseCase
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.MutableNullSafeLiveData
import com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CityListViewModel(
    application: Application,
    private val subscribeCityListUseCase: SubscribeCityListUseCase
) : AndroidViewModel(application) {

    private val _cityList = MutableNullSafeLiveData<StoreState<List<City>>>()
    val cityList: NullSafeLiveData<StoreState<List<City>>> get() = _cityList

    init {
        subscribeCityList()
    }

    private fun subscribeCityList() = viewModelScope.launch {
        subscribeCityListUseCase().collect {
            _cityList.value = it
        }
    }

}
