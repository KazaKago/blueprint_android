package com.kazakago.blueprint.domain.model.hierarchy.about

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.Before
import org.junit.Test
import java.net.URI

class EmailTest {

    private lateinit var email: Email

    @Before
    fun setUp() {
        email = Email("kazakago@gmail.com")
    }

    @Test
    fun rejectInvalidEmail1() {
        invoking { Email("kazakagoATgmail.com") } shouldThrow IllegalArgumentException::class
    }

    @Test
    fun rejectInvalidEmail2() {
        invoking { Email("kazakago@gmailcom") } shouldThrow IllegalArgumentException::class
    }

    @Test
    fun isCorrectEmailURI() {
        val email = Email("kazakago@gmail.com")
        email.toURI() shouldBeEqualTo URI("mailto:kazakago@gmail.com")
    }
}
