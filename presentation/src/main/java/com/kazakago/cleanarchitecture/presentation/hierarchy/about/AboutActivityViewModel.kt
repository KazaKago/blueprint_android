package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kazakago.cleanarchitecture.presentation.livedata.NoValueSingleLiveEvent

class AboutActivityViewModel(application: Application) : AndroidViewModel(application) {

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AboutActivityViewModel(application) as T
        }
    }

    val finish = NoValueSingleLiveEvent()

    fun onClickBackIcon() {
        finish.call()
    }

}
