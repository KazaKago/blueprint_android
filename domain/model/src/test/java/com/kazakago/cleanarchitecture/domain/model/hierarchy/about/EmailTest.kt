package com.kazakago.cleanarchitecture.domain.model.hierarchy.about

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EmailTest {

    private lateinit var email: Email

    @Before
    fun setUp() {
        email = Email("kazakago@gmail.com")
    }

    @Test(expected = IllegalArgumentException::class)
    fun rejectInvalidEmail1() {
        Email("kazakagoATgmail.com")
    }

    @Test(expected = IllegalArgumentException::class)
    fun rejectInvalidEmail2() {
        Email("kazakago@gmailcom")
    }

    @Test
    fun isCorrectEmailURI() {
        Assert.assertEquals("mailto:kazakago@gmail.com", email.toURI().toString())
    }
}