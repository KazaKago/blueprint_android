package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import com.kazakago.cleanarchitecture.presentation.livedata.liveevent.NonNullLiveEvent
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CityListViewModel(
    application: Application,
    private val getCityUseCase: GetCityUseCase
) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val goForecast = NonNullLiveEvent<City>()
    val cityList = NonNullLiveData<List<City>>(emptyList())
    val showToast = NonNullLiveEvent<String>()

    init {
        fetchCityList()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private fun fetchCityList() = launch(context = coroutineContext) {
        try {
            cityList.value = getCityUseCase.execute(Unit)
        } catch (exception: Exception) {
            cityList.value = listOf()
            showToast.call(exception.localizedMessage)
        }
    }

}
