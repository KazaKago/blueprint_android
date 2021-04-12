package com.kazakago.blueprint.data.memory.hierarchy.state

import com.kazakago.blueprint.data.memory.global.DataState
import kotlinx.coroutines.flow.MutableStateFlow

object StateMemory {

    private val state: MutableMap<String, MutableStateFlow<DataState>> = mutableMapOf()

    operator fun get(key: String): MutableStateFlow<DataState> {
        return state.getOrPut(key, { MutableStateFlow(DataState.Fixed) })
    }
}
