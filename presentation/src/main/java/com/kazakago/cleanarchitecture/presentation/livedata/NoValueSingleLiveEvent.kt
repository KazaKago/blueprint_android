package com.kazakago.cleanarchitecture.presentation.livedata

import android.support.annotation.MainThread

open class NoValueSingleLiveEvent : SingleLiveEvent<Unit>() {

    @MainThread
    fun call() {
        super.call(Unit)
    }

}