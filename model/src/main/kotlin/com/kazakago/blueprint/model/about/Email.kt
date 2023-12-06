package com.kazakago.blueprint.model.about

import java.net.URI

@JvmInline
value class Email(val value: String) {

    companion object {
        private val EMAIL_REGEX = Regex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    }

    init {
        require(EMAIL_REGEX.matches(value))
    }

    fun toURI(): URI {
        return URI("mailto:$value")
    }
}
