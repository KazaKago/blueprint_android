package com.kazakago.cleanarchitecture.data.repository.mapper.about

import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.Email
import java.net.URL

internal class DeveloperInfoEntityMapper {

    fun map(name: String, emailAddress: String, webSite: String): DeveloperInfo {
        return DeveloperInfo(
            name = name,
            mailAddress = Email(emailAddress),
            siteUrl = URL(webSite)
        )
    }

}
