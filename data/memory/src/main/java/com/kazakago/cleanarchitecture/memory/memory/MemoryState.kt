package com.kazakago.cleanarchitecture.memory.memory

sealed class MemoryState {
    object Fixed : MemoryState()
    object Loading : MemoryState()
    data class Error(val exception: Exception) : MemoryState()
}
