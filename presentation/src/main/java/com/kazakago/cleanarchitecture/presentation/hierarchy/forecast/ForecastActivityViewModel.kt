package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.livedata.NoValueSingleLiveEvent

class ForecastActivityViewModel(application: Application, city: City) : AndroidViewModel(application) {

    val title = MutableLiveData<CharSequence>()
    val finish = NoValueSingleLiveEvent()

    init {
        title.value = city.name
    }

    fun onClickBackIcon() {
        finish.call()
    }

}
