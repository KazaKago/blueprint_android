package com.kazakago.cleanarchitecture.presentation.presenter.activity

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.presentation.listener.activity.ForecastActivityViewModelListener

class ForecastActivityViewModel(application: Application, city: City) : AndroidViewModel(application) {

    class Factory(private val application: Application, private val city: City) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ForecastActivityViewModel(application, city) as T
        }
    }

    val title = MutableLiveData<CharSequence>()

    var listener: ForecastActivityViewModelListener? = null

    init {
        title.value = city.name
    }

    fun onClickBackIcon() {
        listener?.finish()
    }

}
