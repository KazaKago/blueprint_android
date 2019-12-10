package com.kazakago.cleanarchitecture.model.about

import java.io.Serializable
import java.net.URL

data class DeveloperInfo(
    val name: String,
    val mailAddress: Email,
    val siteUrl: URL
) : Serializable