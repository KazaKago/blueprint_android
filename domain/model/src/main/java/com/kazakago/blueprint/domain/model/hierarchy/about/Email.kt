package com.kazakago.blueprint.domain.model.hierarchy.about

import java.io.Serializable
import java.net.URI

data class Email(val value: String) : Serializable {

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
