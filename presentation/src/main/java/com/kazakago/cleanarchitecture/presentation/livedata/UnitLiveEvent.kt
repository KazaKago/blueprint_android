package com.kazakago.cleanarchitecture.presentation.livedata

import android.support.annotation.MainThread

class UnitLiveEvent : LiveEvent<Unit>() {

    @MainThread
    fun call() {
        super.call(Unit)
    }

    @MainThread
    @Deprecated(
            message = "use call()",
            replaceWith = ReplaceWith("call()"),
            level = DeprecationLevel.WARNING)
    override fun call(t: Unit?) {
        super.call(t)
    }

}