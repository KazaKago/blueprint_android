package com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent

import androidx.annotation.MainThread

class UnitMutableLiveEvent : UnitLiveEvent() {

    @MainThread
    public override fun call() {
        super.call()
    }

}