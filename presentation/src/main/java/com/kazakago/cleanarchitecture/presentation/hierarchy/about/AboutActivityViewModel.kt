package com.kazakago.cleanarchitecture.presentation.hierarchy.about

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.kazakago.cleanarchitecture.presentation.livedata.NoValueSingleLiveEvent

class AboutActivityViewModel(application: Application) : AndroidViewModel(application) {

    val finish = NoValueSingleLiveEvent()

    fun onClickBackIcon() {
        finish.call()
    }

}
