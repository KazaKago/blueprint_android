package com.kazakago.cleanarchitecture.domain.model.global.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <A, Z> Flow<State<A>>.mapContent(transform: suspend (content: A) -> Z): Flow<State<Z>> {
    return map {
        val stateContent = when (val stateContent = it.content) {
            is StateContent.Stored -> StateContent.Stored(transform(stateContent.rawContent))
            is StateContent.NotStored -> StateContent.NotStored<Z>()
        }
        when (it) {
            is State.Fixed -> State.Fixed(stateContent)
            is State.Loading -> State.Loading(stateContent)
            is State.Error -> State.Error(stateContent, it.exception)
        }
    }
}
