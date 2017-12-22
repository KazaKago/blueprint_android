package com.kazakago.cleanarchitecture.data.repository.appinfo

import android.content.Context
import com.kazakago.cleanarchitecture.data.R
import com.kazakago.cleanarchitecture.data.util.StoreUtils
import com.kazakago.cleanarchitecture.data.util.VersionUtils
import com.kazakago.cleanarchitecture.domain.repository.appinfo.AppInfoRepository

class AppInfoRepositoryImpl(private val context: Context) : AppInfoRepository {

    override val playStoreUrl: String
        get() = StoreUtils.getStoreAppLink(context)

    override val mailAddressUrl: String
        get() = context.getString(R.string.developer_mail_address)

    override val officialSiteUrl: String
        get() = context.getString(R.string.developer_website_url)

    override val appVersion: String
        get() = VersionUtils.getVersionName(context)

}
