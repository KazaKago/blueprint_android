package com.kazakago.blueprint.data.repository

import com.kazakago.blueprint.data.resource.AppInfoDao
import com.kazakago.blueprint.data.resource.DeveloperInfoDao
import com.kazakago.blueprint.domain.model.about.*
import com.kazakago.blueprint.domain.repository.AboutRepository
import javax.inject.Inject

class AboutRepositoryImpl @Inject constructor(
    private val appInfoDao: AppInfoDao,
    private val developerInfoDao: DeveloperInfoDao,
) : AboutRepository {

    override suspend fun getAppInfo(): AppInfo {
        return AppInfo(
            versionName = VersionName(appInfoDao.getVersionName()),
            versionCode = VersionCode(appInfoDao.getVersionCode())
        )
    }

    override suspend fun getDeveloperInfo(): DeveloperInfo {
        return DeveloperInfo(
            name = developerInfoDao.getName(),
            mailAddress = Email(developerInfoDao.getEmailAddress().toString()),
            siteUrl = developerInfoDao.getWebSiteUrl(),
        )
    }
}
