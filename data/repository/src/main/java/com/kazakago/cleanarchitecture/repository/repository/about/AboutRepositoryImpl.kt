package com.kazakago.cleanarchitecture.repository.repository.about

import android.content.Context
import com.kazakago.cleanarchitecture.model.about.*
import com.kazakago.cleanarchitecture.repository.R
import com.kazakago.cleanarchitecture.repository.about.AboutRepository
import com.kazakago.cleanarchitecture.resource.dao.device.StoreLinkDao
import com.kazakago.cleanarchitecture.resource.dao.device.VersionDao
import java.net.URL

internal class AboutRepositoryImpl(private val context: Context) : AboutRepository {

    override fun getAppInfo(): AppInfo {
        return AppInfo(
            VersionName(VersionDao.getVersionName(context)),
            VersionCode(VersionDao.getVersionCode(context)),
            StoreLinkDao.getStoreAppLink(context)
        )
    }

    override fun getDeveloperInfo(): DeveloperInfo {
        return DeveloperInfo(
            context.getString(R.string.developer_name),
            Email(context.getString(R.string.developer_mail_address)),
            URL(context.getString(R.string.developer_website_url))
        )
    }

}
