package com.kazakago.cleanarchitecture.domain.model.about

import java.io.Serializable
import java.net.URI
import java.net.URL

data class DeveloperInfo(
        val name: String,
        val mailAddress: String,
        val siteUrl: URL
) : Serializable {

    fun getMailAddressUri(): URI {
        return URI("mailto:$mailAddress")
    }

}