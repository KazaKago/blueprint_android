package com.kazakago.blueprint.data.repository.hierarchy

import com.kazakago.blueprint.data.mapper.entity.about.AppInfoEntityMapper
import com.kazakago.blueprint.data.mapper.entity.about.DeveloperInfoEntityMapper
import com.kazakago.blueprint.data.resource.hierarchy.about.DeveloperInfoDao
import com.kazakago.blueprint.data.resource.hierarchy.device.AppInfoDao
import com.kazakago.blueprint.domain.model.hierarchy.about.AppInfo
import com.kazakago.blueprint.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.blueprint.domain.repository.hierarchy.AboutRepository
import javax.inject.Inject

internal class AboutRepositoryImpl @Inject constructor(
    private val versionDao: AppInfoDao,
    private val developerInfoDao: DeveloperInfoDao,
    private val appInfoEntityMapper: AppInfoEntityMapper,
    private val developerInfoEntityMapper: DeveloperInfoEntityMapper,
) : AboutRepository {

    override suspend fun getAppInfo(): AppInfo {
        return appInfoEntityMapper.map(
            versionName = versionDao.getVersionName(),
            versionCode = versionDao.getVersionCode(),
        )
    }

    override suspend fun getDeveloperInfo(): DeveloperInfo {
        return developerInfoEntityMapper.map(
            name = developerInfoDao.getName(),
            emailAddress = developerInfoDao.getEmailAddress(),
            webSite = developerInfoDao.getWebSiteUrl(),
        )
    }
}
