package com.kazakago.cleanarchitecture.presentation.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import java.util.concurrent.CopyOnWriteArrayList

open class LiveEvent<T> : LiveData<T>() {

    private val dispatchedTagList = CopyOnWriteArrayList<String>()

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
            val internalTag = owner::class.java.name + "#" + tag
            if (!dispatchedTagList.contains(internalTag)) {
                dispatchedTagList.add(internalTag)
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    open fun call(t: T?) {
        dispatchedTagList.clear()
        value = t
    }

}