package com.kazakago.cleanarchitecture.presentation.global.livedata.compositelivedata

import androidx.lifecycle.LifecycleOwner
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.LateInitLiveData
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullLiveData
import com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata.NonNullObserver

fun <A, B> compositeLiveDataOf(first: LateInitLiveData<A>, second: LateInitLiveData<B>) = NonNullPairLiveData(Pair(first, second))
fun <A, B, C> compositeLiveDataOf(first: LateInitLiveData<A>, second: LateInitLiveData<B>, third: LateInitLiveData<C>) = NonNullTripleLiveData(Triple(first, second, third))

class NonNullPairLiveData<A, B>(private val compositeLiveData: Pair<LateInitLiveData<A>, LateInitLiveData<B>>) : NonNullLiveData<Pair<A, B>>(Pair(compositeLiveData.first.value, compositeLiveData.second.value)) {

    override fun observe(owner: LifecycleOwner, nonNullObserver: NonNullObserver<Pair<A, B>>) {
        super.observe(owner, nonNullObserver)
        compositeLiveData.first.observe(owner, NonNullObserver { setCurrentValue() })
        compositeLiveData.second.observe(owner, NonNullObserver { setCurrentValue() })
    }

    override fun observeForever(nonNullObserver: NonNullObserver<Pair<A, B>>) {
        super.observeForever(nonNullObserver)
        compositeLiveData.first.observeForever(NonNullObserver { setCurrentValue() })
        compositeLiveData.second.observeForever(NonNullObserver { setCurrentValue() })
    }

    private fun setCurrentValue() {
        value = Pair(compositeLiveData.first.value, compositeLiveData.second.value)
    }

}

class NonNullTripleLiveData<A, B, C>(private val compositeLiveData: Triple<LateInitLiveData<A>, LateInitLiveData<B>, LateInitLiveData<C>>) : NonNullLiveData<Triple<A, B, C>>(Triple(compositeLiveData.first.value, compositeLiveData.second.value, compositeLiveData.third.value)) {

    override fun observe(owner: LifecycleOwner, nonNullObserver: NonNullObserver<Triple<A, B, C>>) {
        super.observe(owner, nonNullObserver)
        compositeLiveData.first.observe(owner, NonNullObserver { setCurrentValue() })
        compositeLiveData.second.observe(owner, NonNullObserver { setCurrentValue() })
        compositeLiveData.third.observe(owner, NonNullObserver { setCurrentValue() })
    }

    override fun observeForever(nonNullObserver: NonNullObserver<Triple<A, B, C>>) {
        super.observeForever(nonNullObserver)
        compositeLiveData.first.observeForever(NonNullObserver { setCurrentValue() })
        compositeLiveData.second.observeForever(NonNullObserver { setCurrentValue() })
        compositeLiveData.third.observeForever(NonNullObserver { setCurrentValue() })
    }

    private fun setCurrentValue() {
        value = Triple(compositeLiveData.first.value, compositeLiveData.second.value, compositeLiveData.third.value)
    }

}