package com.kazakago.cleanarchitecture.presentation.global.livedata.compositelivedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullObserver

fun <A, B> compositeLiveDataOf(first: LiveData<A>, second: LiveData<B>) = PairLiveData(Pair(first, second))
fun <A, B, C> compositeLiveDataOf(first: LiveData<A>, second: LiveData<B>, third: LiveData<C>) = TripleLiveData(Triple(first, second, third))

class PairLiveData<A, B>(private val compositeLiveData: Pair<LiveData<A>, LiveData<B>>) : NonNullLiveData<Pair<A?, B?>>(Pair(compositeLiveData.first.value, compositeLiveData.second.value)) {

    override fun observe(owner: LifecycleOwner, nonNullObserver: NonNullObserver<Pair<A?, B?>>) {
        super.observe(owner, nonNullObserver)
        compositeLiveData.first.observe(owner, Observer { setCurrentValue() })
        compositeLiveData.second.observe(owner, Observer { setCurrentValue() })
    }

    override fun observeForever(nonNullObserver: NonNullObserver<Pair<A?, B?>>) {
        super.observeForever(nonNullObserver)
        compositeLiveData.first.observeForever { setCurrentValue() }
        compositeLiveData.second.observeForever { setCurrentValue() }
    }

    private fun setCurrentValue() {
        value = Pair(compositeLiveData.first.value, compositeLiveData.second.value)
    }

}

class TripleLiveData<A, B, C>(private val compositeLiveData: Triple<LiveData<A>, LiveData<B>, LiveData<C>>) : NonNullLiveData<Triple<A?, B?, C?>>(Triple(compositeLiveData.first.value, compositeLiveData.second.value, compositeLiveData.third.value)) {

    override fun observe(owner: LifecycleOwner, nonNullObserver: NonNullObserver<Triple<A?, B?, C?>>) {
        super.observe(owner, nonNullObserver)
        compositeLiveData.first.observe(owner, Observer { setCurrentValue() })
        compositeLiveData.second.observe(owner, Observer { setCurrentValue() })
        compositeLiveData.third.observe(owner, Observer { setCurrentValue() })
    }

    override fun observeForever(nonNullObserver: NonNullObserver<Triple<A?, B?, C?>>) {
        super.observeForever(nonNullObserver)
        compositeLiveData.first.observeForever { setCurrentValue() }
        compositeLiveData.second.observeForever { setCurrentValue() }
        compositeLiveData.third.observeForever { setCurrentValue() }
    }

    private fun setCurrentValue() {
        value = Triple(compositeLiveData.first.value, compositeLiveData.second.value, compositeLiveData.third.value)
    }

}