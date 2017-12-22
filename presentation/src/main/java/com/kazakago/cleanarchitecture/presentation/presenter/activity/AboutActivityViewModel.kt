package com.kazakago.cleanarchitecture.presentation.presenter.activity

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleObserver
import com.kazakago.cleanarchitecture.presentation.listener.activity.AboutActivityViewModelListener

class AboutActivityViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    var listener: AboutActivityViewModelListener? = null

    fun onClickBackIcon() {
        listener?.finish()
    }

}
