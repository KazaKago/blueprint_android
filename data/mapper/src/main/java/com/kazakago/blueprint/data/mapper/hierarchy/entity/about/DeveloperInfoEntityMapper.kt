package com.kazakago.blueprint.data.mapper.hierarchy.entity.about

import com.kazakago.blueprint.domain.model.about.DeveloperInfo
import com.kazakago.blueprint.domain.model.about.Email
import java.net.URI
import java.net.URL

class DeveloperInfoEntityMapper {

    fun map(name: String, emailAddress: URI, webSite: URL): DeveloperInfo {
        return DeveloperInfo(
            name = name,
            mailAddress = Email(emailAddress.toString()),
            siteUrl = webSite
        )
    }
}
