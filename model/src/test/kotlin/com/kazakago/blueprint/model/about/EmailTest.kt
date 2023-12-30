package com.kazakago.blueprint.model.about

import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.URI

class EmailTest {

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
        val email = Email("kazakago@gmail.com")
        assertEquals(URI("mailto:kazakago@gmail.com"), email.toURI())
    }
}
