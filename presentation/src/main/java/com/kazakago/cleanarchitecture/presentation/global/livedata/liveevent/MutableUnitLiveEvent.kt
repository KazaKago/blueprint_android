package com.kazakago.cleanarchitecture.presentation.global.livedata.liveevent

import androidx.annotation.MainThread

class MutableUnitLiveEvent : UnitLiveEvent() {

    @MainThread
    public override fun call() {
        super.call()
    }

}