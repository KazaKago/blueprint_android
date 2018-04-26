package com.kazakago.cleanarchitecture.presentation.hierarchy.city

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.kazakago.cleanarchitecture.presentation.livedata.NoValueSingleLiveEvent

class CityListActivityViewModel(application: Application) : AndroidViewModel(application) {

    val toAbout = NoValueSingleLiveEvent()

    fun onClickAboutMenu() {
        toAbout.call()
    }

}
