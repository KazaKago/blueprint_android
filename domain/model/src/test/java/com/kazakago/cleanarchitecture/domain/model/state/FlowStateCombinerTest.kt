package com.kazakago.cleanarchitecture.domain.model.state

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class FlowStateCombinerTest {

    @Test
    fun combineState() = runBlocking {
        val stateFlow1 = flow { emit(State.Fixed<Int>(StateContent.Stored(0))) }
        val stateFlow2 = flow { emit(State.Fixed<Int>(StateContent.Stored(2))) }
        val combinedStateFlow = stateFlow1.combineState(stateFlow2) { content1, content2 ->
            assertThat(content1, `is`(0))
            assertThat(content2, `is`(2))
            content1 to content2
        }
        combinedStateFlow.collect {
            assertThat(it, `is`(instanceOf(State.Fixed::class.java)))
        }
    }

    @Test
    fun combineManyState() = runBlocking {
        val stateFlow1 = flow { emit(State.Fixed<Int>(StateContent.Stored(0))) }
        val stateFlow2 = flow { emit(State.Fixed<Int>(StateContent.Stored(2))) }
        val stateFlow3 = flow { emit(State.Fixed<Int>(StateContent.Stored(4))) }
        val stateFlow4 = flow { emit(State.Fixed<Int>(StateContent.Stored(6))) }
        val stateFlow5 = flow { emit(State.Fixed<Int>(StateContent.Stored(8))) }
        val combinedStateFlow = stateFlow1.combineState(stateFlow2, stateFlow3, stateFlow4, stateFlow5) { content1, content2, content3, content4, content5 ->
            assertThat(content1, `is`(0))
            assertThat(content2, `is`(2))
            assertThat(content3, `is`(4))
            assertThat(content4, `is`(6))
            assertThat(content5, `is`(8))
            content1 to content2
        }
        combinedStateFlow.collect {
            assertThat(it, `is`(instanceOf(State.Fixed::class.java)))
        }
    }

}