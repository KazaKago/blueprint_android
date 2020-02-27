package com.kazakago.cleanarchitecture.data.memory.global

import kotlinx.coroutines.channels.ConflatedBroadcastChannel

fun <K> MutableMap<K, ConflatedBroadcastChannel<DataState>>.getOrPut(key: K): ConflatedBroadcastChannel<DataState> {
    return getOrPut(key, { ConflatedBroadcastChannel(DataState.Fixed) })
}
