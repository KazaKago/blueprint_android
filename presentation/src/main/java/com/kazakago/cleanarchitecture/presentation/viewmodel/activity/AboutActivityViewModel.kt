package com.kazakago.cleanarchitecture.presentation.viewmodel.activity

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kazakago.cleanarchitecture.presentation.listener.activity.AboutActivityViewModelListener

class AboutActivityViewModel(application: Application) : AndroidViewModel(application) {

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AboutActivityViewModel(application) as T
        }
    }

    var listener: AboutActivityViewModelListener? = null

    fun onClickBackIcon() {
        listener?.finish()
    }

}
