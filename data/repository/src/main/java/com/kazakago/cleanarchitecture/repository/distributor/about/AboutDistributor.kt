package com.kazakago.cleanarchitecture.repository.distributor.about

import android.content.Context
import com.kazakago.cleanarchitecture.model.about.*
import com.kazakago.cleanarchitecture.repository.R
import com.kazakago.cleanarchitecture.resource.dao.device.StoreLinkDao
import com.kazakago.cleanarchitecture.resource.dao.device.VersionDao
import java.net.URL

internal class AboutDistributor(private val context: Context) {

    private val versionDao = VersionDao(context)
    private val storeLinkDao = StoreLinkDao(context)

    fun getAppInfo(): AppInfo {
        return AppInfo(
            VersionName(versionDao.getVersionName()),
            VersionCode(versionDao.getVersionCode()),
            storeLinkDao.getStoreAppLink()
        )
    }

    fun getDeveloperInfo(): DeveloperInfo {
        return DeveloperInfo(
            context.getString(R.string.developer_name),
            Email(context.getString(R.string.developer_mail_address)),
            URL(context.getString(R.string.developer_website_url))
        )
    }

}
