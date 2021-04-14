package com.kazakago.blueprint.data.mapper.hierarchy.entity.about

import com.kazakago.blueprint.domain.model.about.AppInfo
import com.kazakago.blueprint.domain.model.about.VersionName
import java.net.URI

class AppInfoEntityMapper {

    fun map(versionName: String, storeAppLink: URI): AppInfo {
        return AppInfo(
            versionName = VersionName(versionName),
            playStoreUri = storeAppLink
        )
    }
}
