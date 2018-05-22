package com.kazakago.cleanarchitecture.presentation.livedata

import android.support.annotation.MainThread

class UnitLiveEvent : LiveEvent<Unit>() {

    @Deprecated(
            message = "use call()",
            replaceWith = ReplaceWith("call()"),
            level = DeprecationLevel.WARNING)
    override fun call(t: Unit?) {
        super.call(t)
    }

    @MainThread
    fun call() {
        super.call(Unit)
    }

    @Deprecated(
            message = "use post()",
            replaceWith = ReplaceWith("post()"),
            level = DeprecationLevel.WARNING)
    override fun post(t: Unit?) {
        super.post(t)
    }

    fun post() {
        super.post(Unit)
    }

}