package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kazakago.cleanarchitecture.presentation.livedata.NoValueSingleLiveEvent

class CityListActivityViewModel(application: Application) : AndroidViewModel(application) {

    class Factory(private val application: Application): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CityListActivityViewModel(application) as T
        }
    }

    val toAbout = NoValueSingleLiveEvent()

    fun onClickAboutMenu() {
        toAbout.call()
    }

}
