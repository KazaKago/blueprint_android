package com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class LateInitLiveData<T> : MutableLiveData<T>() {

    @MainThread
    @Deprecated(
            message = "use observe for NonNull.",
            replaceWith = ReplaceWith("observe(owner, NonNullObserver)"),
            level = DeprecationLevel.HIDDEN)
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    @MainThread
    @Deprecated(
            message = "use observeForever for NonNull.",
            replaceWith = ReplaceWith("observeForever(nonNullObserver)"),
            level = DeprecationLevel.HIDDEN)
    override fun observeForever(observer: Observer<in T>) {
        super.observeForever(observer)
    }

    @MainThread
    open fun observe(owner: LifecycleOwner, nonNullObserver: NonNullObserver<T>) {
        super.observe(owner, Observer { nonNullObserver.onChanged(it!!) })
    }

    @MainThread
    open fun observeForever(nonNullObserver: NonNullObserver<T>) {
        super.observeForever { nonNullObserver.onChanged(it!!) }
    }

    override fun getValue(): T {
        return super.getValue() ?: throw UninitializedPropertyAccessException()
    }

    @MainThread
    override fun setValue(value: T) {
        super.setValue(value)
    }

    override fun postValue(value: T) {
        super.postValue(value)
    }

}