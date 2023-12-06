package com.kazakago.blueprint.model.about

import java.net.URL

data class DeveloperInfo(
    val name: String,
    val mailAddress: Email,
    val siteUrl: URL,
)
