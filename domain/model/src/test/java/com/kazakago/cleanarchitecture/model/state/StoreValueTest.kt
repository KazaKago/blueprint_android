package com.kazakago.cleanarchitecture.model.state

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.time.Duration
import java.time.LocalDateTime

class StoreValueTest {

    private lateinit var storeValue: StoreValue.Stored<Int>

    @Before
    fun setUp() {
        val oneMinuteAgo = LocalDateTime.now() - Duration.ofMinutes(1)
        storeValue = StoreValue.Stored(value = 24, storedTime = oneMinuteAgo)
    }

    @Test
    fun expired() {
        Assert.assertEquals(true, storeValue.isExpired(Duration.ofSeconds(10)))
        Assert.assertEquals(true, storeValue.isExpired(Duration.ofSeconds(30)))
    }

    @Test
    fun notExpired() {
        Assert.assertEquals(false, storeValue.isExpired(Duration.ofMinutes(3)))
        Assert.assertEquals(false, storeValue.isExpired(Duration.ofMinutes(5)))
    }

}