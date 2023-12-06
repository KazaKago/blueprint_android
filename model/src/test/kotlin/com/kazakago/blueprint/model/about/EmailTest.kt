package com.kazakago.blueprint.model.about

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.Test
import java.net.URI

class EmailTest {

    @Test
    fun rejectInvalidEmail1() {
        shouldThrow<IllegalArgumentException> { Email("kazakagoATgmail.com") }
    }

    @Test
    fun rejectInvalidEmail2() {
        shouldThrow<IllegalArgumentException> { Email("kazakago@gmailcom") }
    }

    @Test
    fun isCorrectEmailURI() {
        val email = Email("kazakago@gmail.com")
        email.toURI() shouldBe URI("mailto:kazakago@gmail.com")
    }
}
