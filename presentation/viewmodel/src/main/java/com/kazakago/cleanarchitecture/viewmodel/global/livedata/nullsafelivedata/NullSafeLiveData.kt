package com.kazakago.cleanarchitecture.viewmodel.global.livedata.nullsafelivedata

import androidx.lifecycle.LiveData
import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.state.StoreState

open class NullSafeLiveData<T> : LiveData<T> {

    constructor(value: StoreState.Fixed<AppInfo>) : super(value)

    constructor() : super()

    @Suppress("UNCHECKED_CAST")
    override fun getValue(): T {
        return super.getValue() as T
    }

}