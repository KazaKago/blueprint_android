package com.kazakago.cleanarchitecture.presentation.global.livedata.nullsafelivedata

import androidx.lifecycle.LiveData

open class NullSafeLiveData<T> : LiveData<T> {

    constructor(value: T) : super(value)

    constructor() : super()

    @Suppress("UNCHECKED_CAST")
    override fun getValue(): T {
        return super.getValue() as T
    }

}