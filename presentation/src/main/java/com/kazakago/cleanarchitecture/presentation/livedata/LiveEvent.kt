package com.kazakago.cleanarchitecture.presentation.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import java.util.concurrent.CopyOnWriteArrayList

open class LiveEvent<T> : LiveData<T>() {

    private val pending = CopyOnWriteArrayList<String>()

    @MainThread
    @Deprecated(
            message = "Multiple observers registered but only one will be notified of changes. set tags for each observer.",
            replaceWith = ReplaceWith("observe(owner, \"\", observer)"),
            level = DeprecationLevel.WARNING)
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        observe(owner, "", observer)
    }

    @MainThread
    open fun observe(owner: LifecycleOwner, tag: String, observer: Observer<T>) {
        super.observe(owner, Observer<T> {
            if (!pending.contains(tag)) {
                pending.add(tag)
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    open fun call(t: T?) {
        pending.clear()
        value = t
    }

    open fun post(t: T?) {
        pending.clear()
        postValue(t)
    }

}