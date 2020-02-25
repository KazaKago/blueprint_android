package com.kazakago.cleanarchitecture.data.memory.global

sealed class DataState {
    object Fixed : DataState()
    object Loading : DataState()
    data class Error(val exception: Exception) : DataState()
}
