package com.kazakago.cleanarchitecture.data.repository

import android.content.Context

import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.data.util.StoreUtils
import com.kazakago.cleanarchitecture.data.util.VersionUtils
import com.kazakago.cleanarchitecture.domain.repository.AppInfoRepository

/**
 * AppInfo Repository Implement
 *
 * Created by tamura_k on 2016/05/27.
 */
class AppInfoRepositoryImpl(private val context: Context) : AppInfoRepository {

    override val playStoreUrl: String
        get() = StoreUtils.getStoreAppLink(context = context)

    override val mailAddressUrl: String
        get() = context.getString(R.string.developer_mail_address)

    override val officialSiteUrl: String
        get() = context.getString(R.string.developer_website_url)

    override val appVersion: String
        get() = VersionUtils.getVersionName(context = context)

}