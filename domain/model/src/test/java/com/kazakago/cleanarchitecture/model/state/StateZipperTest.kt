package com.kazakago.cleanarchitecture.model.state

import com.os.operando.guild.kt.to
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class StateZipperTest {

    @Test
    fun zipFixedFixed() {
        val state1 = State.Fixed<Int>(StateContent.NotStored())
        val state2 = State.Fixed<Int>(StateContent.NotStored())
        val zippedState = state1.zip(state2) { content1, content2 -> content1 to content2 }
        assertThat(zippedState, `is`(instanceOf(State.Fixed::class.java)))
    }

    @Test
    fun zipFixedLoading() {
        val state1 = State.Fixed<Int>(StateContent.NotStored())
        val state2 = State.Loading<Int>(StateContent.NotStored())
        val zippedState = state1.zip(state2) { content1, content2 -> content1 to content2 }
        assertThat(zippedState, `is`(instanceOf(State.Loading::class.java)))
    }

    @Test
    fun zipFixedError() {
        val state1 = State.Fixed<Int>(StateContent.NotStored())
        val state2 = State.Error<Int>(StateContent.NotStored(), IllegalStateException())
        val zippedState = state1.zip(state2) { content1, content2 -> content1 to content2 }
        assertThat(zippedState, `is`(instanceOf(State.Error::class.java)))
    }

    @Test
    fun zipLoadingFixed() {
        val state1 = State.Loading<Int>(StateContent.NotStored())
        val state2 = State.Fixed<Int>(StateContent.NotStored())
        val zippedState = state1.zip(state2) { content1, content2 -> content1 to content2 }
        assertThat(zippedState, `is`(instanceOf(State.Loading::class.java)))
    }

    @Test
    fun zipLoadingLoading() {
        val state1 = State.Loading<Int>(StateContent.NotStored())
        val state2 = State.Loading<Int>(StateContent.NotStored())
        val zippedState = state1.zip(state2) { content1, content2 -> content1 to content2 }
        assertThat(zippedState, `is`(instanceOf(State.Loading::class.java)))
    }

    @Test
    fun zipLoadingError() {
        val state1 = State.Loading<Int>(StateContent.NotStored())
        val state2 = State.Error<Int>(StateContent.NotStored(), IllegalStateException())
        val zippedState = state1.zip(state2) { content1, content2 -> content1 to content2 }
        assertThat(zippedState, `is`(instanceOf(State.Error::class.java)))
    }

    @Test
    fun zipErrorFixed() {
        val state1 = State.Error<Int>(StateContent.NotStored(), IllegalStateException())
        val state2 = State.Fixed<Int>(StateContent.NotStored())
        val zippedState = state1.zip(state2) { content1, content2 -> content1 to content2 }
        assertThat(zippedState, `is`(instanceOf(State.Error::class.java)))
    }

    @Test
    fun zipErrorLoading() {
        val state1 = State.Error<Int>(StateContent.NotStored(), IllegalStateException())
        val state2 = State.Loading<Int>(StateContent.NotStored())
        val zippedState = state1.zip(state2) { content1, content2 -> content1 to content2 }
        assertThat(zippedState, `is`(instanceOf(State.Error::class.java)))
    }

    @Test
    fun zipErrorError() {
        val state1 = State.Error<Int>(StateContent.NotStored(), IllegalStateException())
        val state2 = State.Error<Int>(StateContent.NotStored(), IllegalAccessException())
        val zippedState = state1.zip(state2) { content1, content2 -> content1 to content2 }
        assertThat(zippedState, `is`(instanceOf(State.Error::class.java)))
        assertThat((zippedState as State.Error).exception, `is`(instanceOf(IllegalStateException::class.java)))
    }

    @Test
    fun zipManyFixed() {
        val state1 = State.Fixed<Int>(StateContent.NotStored())
        val state2 = State.Fixed<Int>(StateContent.NotStored())
        val state3 = State.Fixed<Int>(StateContent.NotStored())
        val state4 = State.Fixed<Int>(StateContent.NotStored())
        val state5 = State.Fixed<Int>(StateContent.NotStored())
        val zippedState = state1.zip(state2, state3, state4, state5) { content1, content2, content3, content4, content5 -> content1 to content2 to content3 to content4 to content5 }
        assertThat(zippedState, `is`(instanceOf(State.Fixed::class.java)))
    }

    @Test
    fun zipManyFixedLoading() {
        val state1 = State.Fixed<Int>(StateContent.NotStored())
        val state2 = State.Loading<Int>(StateContent.NotStored())
        val state3 = State.Fixed<Int>(StateContent.NotStored())
        val state4 = State.Loading<Int>(StateContent.NotStored())
        val state5 = State.Fixed<Int>(StateContent.NotStored())
        val zippedState = state1.zip(state2, state3, state4, state5) { content1, content2, content3, content4, content5 -> content1 to content2 to content3 to content4 to content5 }
        assertThat(zippedState, `is`(instanceOf(State.Loading::class.java)))
    }

    @Test
    fun zipManyFixedLoadingError() {
        val state1 = State.Fixed<Int>(StateContent.NotStored())
        val state2 = State.Loading<Int>(StateContent.NotStored())
        val state3 = State.Error<Int>(StateContent.NotStored(), IllegalStateException())
        val state4 = State.Loading<Int>(StateContent.NotStored())
        val state5 = State.Fixed<Int>(StateContent.NotStored())
        val zippedState = state1.zip(state2, state3, state4, state5) { content1, content2, content3, content4, content5 -> content1 to content2 to content3 to content4 to content5 }
        assertThat(zippedState, `is`(instanceOf(State.Error::class.java)))
        assertThat((zippedState as State.Error).exception, `is`(instanceOf(IllegalStateException::class.java)))
    }

}