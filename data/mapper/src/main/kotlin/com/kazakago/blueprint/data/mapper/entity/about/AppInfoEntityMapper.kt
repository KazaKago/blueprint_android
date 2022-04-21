package com.kazakago.blueprint.data.mapper.entity.about

import com.kazakago.blueprint.domain.model.hierarchy.about.AppInfo
import com.kazakago.blueprint.domain.model.hierarchy.about.VersionCode
import com.kazakago.blueprint.domain.model.hierarchy.about.VersionName
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInfoEntityMapper @Inject constructor() {

    fun map(versionName: String, versionCode: Long): AppInfo {
        return AppInfo(
            versionName = VersionName(versionName),
            versionCode = VersionCode(versionCode)
        )
    }
}
