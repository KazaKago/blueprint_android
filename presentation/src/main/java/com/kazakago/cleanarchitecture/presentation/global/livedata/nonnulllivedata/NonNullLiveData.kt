package com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata

open class NonNullLiveData<T>(initialValue: T) : LateInitLiveData<T>() {

    init {
        value = initialValue
    }

}