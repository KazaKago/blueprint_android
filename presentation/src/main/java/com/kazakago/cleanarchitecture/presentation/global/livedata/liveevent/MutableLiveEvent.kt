package com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent

import androidx.annotation.MainThread

class MutableLiveEvent<T> : LiveEvent<T>() {

    @MainThread
    public override fun call(t: T?) {
        super.call(t)
    }

}