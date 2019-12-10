package com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata

import androidx.annotation.MainThread

class MutableNullSafeLiveData<T> : NullSafeLiveData<T> {

    constructor(value: T) : super(value)

    constructor() : super()

    @MainThread
    public override fun setValue(value: T) {
        super.setValue(value)
    }

    public override fun postValue(value: T) {
        super.postValue(value)
    }

}