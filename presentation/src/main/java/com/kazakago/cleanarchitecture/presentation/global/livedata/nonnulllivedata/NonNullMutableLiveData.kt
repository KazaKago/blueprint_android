package com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata

import androidx.annotation.MainThread

class NonNullMutableLiveData<T>(initialValue: T) : NonNullLiveData<T>(initialValue) {

    @MainThread
    public override fun setValue(value: T) {
        super.setValue(value)
    }

    public override fun postValue(value: T) {
        super.postValue(value)
    }

}