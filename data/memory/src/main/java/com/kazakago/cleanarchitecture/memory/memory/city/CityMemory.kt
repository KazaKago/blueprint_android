package com.kazakago.cleanarchitecture.memory.memory.city

import com.kazakago.cleanarchitecture.memory.memory.MemoryState
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

object CityMemory {
    val cityListState = ConflatedBroadcastChannel<MemoryState>(MemoryState.Fixed)
}
