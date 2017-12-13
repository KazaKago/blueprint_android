package com.kazakago.cleanarchitecture.presentation.presenter.activity

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleObserver
import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.MainActivityViewModelListener

class MainActivityViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    var listener: MainActivityViewModelListener? = null

    fun onClickAboutMenu() {
        listener?.toAboutActivity()
    }

}
