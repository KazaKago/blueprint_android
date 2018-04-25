package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.livedata.NoValueSingleLiveEvent

class ForecastActivityViewModel(application: Application, city: City) : AndroidViewModel(application) {

    class Factory(private val application: Application, private val city: City) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ForecastActivityViewModel(application, city) as T
        }
    }

    val title = MutableLiveData<CharSequence>()
    val finish = NoValueSingleLiveEvent()

    init {
        title.value = city.name
    }

    fun onClickBackIcon() {
        finish.call()
    }

}
