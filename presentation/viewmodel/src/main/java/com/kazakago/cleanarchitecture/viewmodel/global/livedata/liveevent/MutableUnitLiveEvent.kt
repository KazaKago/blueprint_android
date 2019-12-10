package com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent

import androidx.annotation.MainThread

class MutableUnitLiveEvent : UnitLiveEvent() {

    @MainThread
    public override fun call() {
        super.call()
    }

}