package com.kazakago.blueprint.ui.feature.about

import androidx.compose.runtime.Composable
import com.kazakago.blueprint.model.about.AboutInfo
import com.kazakago.blueprint.model.about.AppInfo
import com.kazakago.blueprint.model.about.DeveloperInfo
import com.kazakago.blueprint.model.about.Email
import com.kazakago.blueprint.model.about.VersionCode
import com.kazakago.blueprint.model.about.VersionName
import com.kazakago.blueprint.ui.global.di.LocalAppInfoDao
import com.kazakago.blueprint.ui.global.di.LocalDeveloperInfoDao
import com.kazakago.swr.compose.state.SWRState
import com.kazakago.swr.compose.useSWR

@Composable
fun useAboutInfo(): SWRState<String, AboutInfo> {
    val appInfoDao = LocalAppInfoDao.current
    val developerInfoDao = LocalDeveloperInfoDao.current
    return useSWR(
        key = "about",
        fetcher = {
            AboutInfo(
                appInfo = AppInfo(
                    versionName = VersionName(appInfoDao.getVersionName()),
                    versionCode = VersionCode(appInfoDao.getVersionCode()),
                ),
                developerInfo = DeveloperInfo(
                    name = developerInfoDao.getName(),
                    mailAddress = Email(developerInfoDao.getEmailAddress().toString()),
                    siteUrl = developerInfoDao.getWebSiteUrl(),
                ),
            )
        },
    )
}
