package com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner

@MainThread
inline fun <T> LateInitLiveData<T>.observe(owner: LifecycleOwner, crossinline onChanged: (T) -> Unit): NonNullObserver<T> {
    val wrappedObserver = NonNullObserver<T> { t -> onChanged.invoke(t) }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

@MainThread
inline fun <T> LateInitLiveData<T>.observeForever(crossinline onChanged: (T) -> Unit): NonNullObserver<T> {
    val wrappedObserver = NonNullObserver<T> { t -> onChanged.invoke(t) }
    observeForever(wrappedObserver)
    return wrappedObserver
}
