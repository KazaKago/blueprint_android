package com.kazakago.cleanarchitecture.data.memory.hierarchy.state

import com.kazakago.cleanarchitecture.data.memory.global.DataState
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

object StateMemory {

    private val state: MutableMap<String, ConflatedBroadcastChannel<DataState>> = mutableMapOf()

    operator fun get(key: String): ConflatedBroadcastChannel<DataState> {
        return state.getOrPut(key, { ConflatedBroadcastChannel(DataState.Fixed) })
    }

}
