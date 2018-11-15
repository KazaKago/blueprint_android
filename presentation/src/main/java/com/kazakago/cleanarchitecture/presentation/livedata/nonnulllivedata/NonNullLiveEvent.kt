package com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CopyOnWriteArrayList

open class NonNullLiveEvent<T> : LiveData<T>() {

    private val observers = mutableMapOf<NonNullObserver<T>, Observer<T>>()
    private val dispatchedTagList = CopyOnWriteArrayList<String>()

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
    open fun observe(owner: LifecycleOwner, tag: String, nonNullObserver: NonNullObserver<T>) {
        val observer = Observer<T> {
            val internalTag = owner::class.java.name + "#" + tag
            if (!dispatchedTagList.contains(internalTag)) {
                dispatchedTagList.add(internalTag)
                nonNullObserver.onChanged(it!!)
            }
        }
        observers[nonNullObserver] = observer
        super.observe(owner, observer)
    }

    @MainThread
    open fun observeForever(tag: String, nonNullObserver: NonNullObserver<T>) {
        val observer = Observer<T> {
            if (!dispatchedTagList.contains(tag)) {
                dispatchedTagList.add(tag)
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
        dispatchedTagList.clear()
        value = t
    }

}