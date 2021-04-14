package com.kazakago.blueprint.data.repository.hierarchy

import com.kazakago.blueprint.data.mapper.hierarchy.entity.about.AppInfoEntityMapper
import com.kazakago.blueprint.data.mapper.hierarchy.entity.about.DeveloperInfoEntityMapper
import com.kazakago.blueprint.data.resource.hierarchy.about.DeveloperInfoDao
import com.kazakago.blueprint.data.resource.hierarchy.device.StoreDao
import com.kazakago.blueprint.data.resource.hierarchy.device.VersionDao
import com.kazakago.blueprint.domain.model.about.AppInfo
import com.kazakago.blueprint.domain.model.about.DeveloperInfo
import com.kazakago.blueprint.domain.repository.AboutRepository

internal class AboutRepositoryImpl(
    private val versionDao: VersionDao,
    private val storeDao: StoreDao,
    private val developerInfoDao: DeveloperInfoDao,
    private val appInfoEntityMapper: AppInfoEntityMapper,
    private val developerInfoEntityMapper: DeveloperInfoEntityMapper,
) : AboutRepository {

    override suspend fun getAppInfo(): AppInfo {
        return appInfoEntityMapper.map(
            versionName = versionDao.getVersionName(),
            storeAppLink = storeDao.getStoreAppLink(),
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
