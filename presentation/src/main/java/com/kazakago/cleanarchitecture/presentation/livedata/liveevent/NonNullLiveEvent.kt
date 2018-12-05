package com.kazakago.cleanarchitecture.presentation.livedata.liveevent

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata.NonNullObserver

open class NonNullLiveEvent<T> : LiveData<T>() {

    private val observers = mutableMapOf<NonNullObserver<T>, Observer<T>>()
    private val dispatchedTagSet = mutableSetOf<String>()

    @MainThread
    @Deprecated(
        message = "use observe for NonNull.",
        replaceWith = ReplaceWith("observe(owner, \"\", nonNullObserver)"),
        level = DeprecationLevel.HIDDEN
    )
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        observe(owner, "", NonNullObserver { observer.onChanged(it) })
    }

    @MainThread
    @Deprecated(
        message = "use observeForever for NonNull.",
        replaceWith = ReplaceWith("observeForever(\"\", nonNullObserver)"),
        level = DeprecationLevel.HIDDEN
    )
    override fun observeForever(observer: Observer<in T>) {
        super.observeForever(observer)
    }

    @MainThread
    @Deprecated(
        message = "use observe for NonNull.",
        replaceWith = ReplaceWith("removeObserver(nonNullObserver)"),
        level = DeprecationLevel.HIDDEN
    )
    override fun removeObserver(observer: Observer<in T>) {
        super.removeObserver(observer)
    }

    @MainThread
    @Deprecated(
        message = "should not use getValue() in LiveEvent.",
        level = DeprecationLevel.HIDDEN
    )
    override fun getValue(): T? {
        return super.getValue()
    }

    @MainThread
    open fun observe(owner: LifecycleOwner, tag: String, nonNullObserver: NonNullObserver<T>) {
        val observer = Observer<T> {
            val internalTag = owner::class.java.name + "#" + tag
            if (!dispatchedTagSet.contains(internalTag)) {
                dispatchedTagSet.add(internalTag)
                nonNullObserver.onChanged(it!!)
            }
        }
        observers[nonNullObserver] = observer
        super.observe(owner, observer)
    }

    @MainThread
    open fun observeForever(tag: String, nonNullObserver: NonNullObserver<T>) {
        val observer = Observer<T> {
            if (!dispatchedTagSet.contains(tag)) {
                dispatchedTagSet.add(tag)
                nonNullObserver.onChanged(it!!)
            }
        }
        observers[nonNullObserver] = observer
        super.observeForever(observer)
    }

    @MainThread
    open fun removeObserver(nonNullObserver: NonNullObserver<T>) {
        val observer = observers[nonNullObserver]
        if (observer != null) {
            observers.remove(nonNullObserver)
            super.removeObserver(observer)
        }
    }

    @MainThread
    open fun call(t: T) {
        dispatchedTagSet.clear()
        value = t
    }

}