package com.kazakago.cleanarchitecture.data.repository.about

import android.content.Context
import com.kazakago.cleanarchitecture.data.R
import com.kazakago.cleanarchitecture.data.util.StoreUtils
import com.kazakago.cleanarchitecture.data.util.VersionUtils
import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.repository.about.AboutRepository
import java.net.URL

class AboutRepositoryImpl(private val context: Context) : AboutRepository {

    override fun getAppInfo(): AppInfo {
        return AppInfo(VersionUtils.getVersionName(context),
                VersionUtils.getVersionCode(context),
                StoreUtils.getStoreAppLink(context))
    }

    override fun getDeveloperInfo(): DeveloperInfo {
        return DeveloperInfo(context.getString(R.string.developer_name),
                context.getString(R.string.developer_mail_address),
                URL(context.getString(R.string.developer_website_url)))
    }

}
