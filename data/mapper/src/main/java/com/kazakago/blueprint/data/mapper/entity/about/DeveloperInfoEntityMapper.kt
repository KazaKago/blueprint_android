package com.kazakago.blueprint.data.mapper.entity.about

import com.kazakago.blueprint.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.blueprint.domain.model.hierarchy.about.Email
import java.net.URI
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeveloperInfoEntityMapper @Inject constructor() {

    fun map(name: String, emailAddress: URI, webSite: URL): DeveloperInfo {
        return DeveloperInfo(
            name = name,
            mailAddress = Email(emailAddress.toString()),
            siteUrl = webSite
        )
    }
}
