package com.kazakago.cleanarchitecture.viewmodel.global.livedata.liveevent

import androidx.annotation.MainThread

open class UnitLiveEvent : LiveEvent<Unit>() {

    @MainThread
    @Deprecated(
        message = "use call()",
        replaceWith = ReplaceWith("call()"),
        level = DeprecationLevel.HIDDEN
    )
    override fun call(t: Unit) {
        super.call(t)
    }

    @MainThread
    protected open fun call() {
        super.call(Unit)
    }

}