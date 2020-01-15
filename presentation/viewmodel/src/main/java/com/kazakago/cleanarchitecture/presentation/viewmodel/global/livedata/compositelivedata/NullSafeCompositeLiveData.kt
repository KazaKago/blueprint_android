package com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.compositelivedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.kazakago.cleanarchitecture.presentation.viewmodel.global.livedata.nullsafelivedata.NullSafeLiveData

fun <A, B> compositeLiveDataOf(first: NullSafeLiveData<A>, second: NullSafeLiveData<B>) = NullSafePairLiveData(Pair(first, second))
fun <A, B, C> compositeLiveDataOf(first: NullSafeLiveData<A>, second: NullSafeLiveData<B>, third: NullSafeLiveData<C>) = NullSafeTripleLiveData(Triple(first, second, third))

class NullSafePairLiveData<A, B>(private val compositeLiveData: Pair<NullSafeLiveData<A>, NullSafeLiveData<B>>) : NullSafeLiveData<Pair<A, B>>(Pair(compositeLiveData.first.value, compositeLiveData.second.value)) {

    override fun observe(owner: LifecycleOwner, observer: Observer<in Pair<A, B>>) {
        super.observe(owner, observer)
        compositeLiveData.first.observe(owner, Observer { setCurrentValue() })
        compositeLiveData.second.observe(owner, Observer { setCurrentValue() })
    }

    override fun observeForever(observer: Observer<in Pair<A, B>>) {
        super.observeForever(observer)
        compositeLiveData.first.observeForever { setCurrentValue() }
        compositeLiveData.second.observeForever { setCurrentValue() }
    }

    private fun setCurrentValue() {
        value = Pair(compositeLiveData.first.value, compositeLiveData.second.value)
    }

}

class NullSafeTripleLiveData<A, B, C>(private val compositeLiveData: Triple<NullSafeLiveData<A>, NullSafeLiveData<B>, NullSafeLiveData<C>>) : NullSafeLiveData<Triple<A, B, C>>(Triple(compositeLiveData.first.value, compositeLiveData.second.value, compositeLiveData.third.value)) {

    override fun observe(owner: LifecycleOwner, observer: Observer<in Triple<A, B, C>>) {
        super.observe(owner, observer)
        compositeLiveData.first.observe(owner, Observer { setCurrentValue() })
        compositeLiveData.second.observe(owner, Observer { setCurrentValue() })
        compositeLiveData.third.observe(owner, Observer { setCurrentValue() })
    }

    override fun observeForever(observer: Observer<in Triple<A, B, C>>) {
        super.observeForever(observer)
        compositeLiveData.first.observeForever { setCurrentValue() }
        compositeLiveData.second.observeForever { setCurrentValue() }
        compositeLiveData.third.observeForever { setCurrentValue() }
    }

    private fun setCurrentValue() {
        value = Triple(compositeLiveData.first.value, compositeLiveData.second.value, compositeLiveData.third.value)
    }

}