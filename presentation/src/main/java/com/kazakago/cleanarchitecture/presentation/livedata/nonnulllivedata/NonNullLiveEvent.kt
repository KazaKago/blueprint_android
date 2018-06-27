package com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CopyOnWriteArrayList

open class NonNullLiveEvent<T> : LiveData<T>() {

    private val dispatchedTagList = CopyOnWriteArrayList<String>()

    @MainThread
    @Deprecated(
            message = "Multiple observers registered but only one will be notified of changes. set tags for each observer.",
            replaceWith = ReplaceWith("observe(owner, \"\", nonNullObserver)"),
            level = DeprecationLevel.HIDDEN)
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        observe(owner, "", NonNullObserver { observer.onChanged(it) })
    }

    @MainThread
    open fun observe(owner: LifecycleOwner, tag: String, nonNullObserver: NonNullObserver<in T>) {
        super.observe(owner, Observer<T> {
            val internalTag = owner::class.java.name + "#" + tag
            if (!dispatchedTagList.contains(internalTag)) {
                dispatchedTagList.add(internalTag)
                nonNullObserver.onChanged(it!!)
            }
        })
    }

    @MainThread
    open fun call(t: T) {
        dispatchedTagList.clear()
        value = t
    }

}