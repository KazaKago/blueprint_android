package com.kazakago.blueprint.data.mapper.entity.about

import com.kazakago.blueprint.domain.model.hierarchy.about.AppInfo
import com.kazakago.blueprint.domain.model.hierarchy.about.VersionName
import java.net.URI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInfoEntityMapper @Inject constructor() {

    fun map(versionName: String, storeAppLink: URI): AppInfo {
        return AppInfo(
            versionName = VersionName(versionName),
            playStoreUri = storeAppLink
        )
    }
}
