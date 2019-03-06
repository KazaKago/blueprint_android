package com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent

import androidx.annotation.MainThread

class NonNullMutableLiveEvent<T> : NonNullLiveEvent<T>() {

    @MainThread
    public override fun call(t: T) {
        super.call(t)
    }

}