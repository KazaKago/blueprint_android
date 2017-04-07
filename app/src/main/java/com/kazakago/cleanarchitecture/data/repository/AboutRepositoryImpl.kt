package com.kazakago.cleanarchitecture.data.repository

import android.content.Context

import com.kazakago.cleanarchitecture.R
import com.kazakago.cleanarchitecture.data.util.StoreUtils
import com.kazakago.cleanarchitecture.data.util.VersionUtils
import com.kazakago.cleanarchitecture.domain.repository.AboutRepository

/**
 * About Repository Implement
 *
 *
 * Created by tamura_k on 2016/05/27.
 */
class AboutRepositoryImpl(private val context: Context) : AboutRepository {

    override val playStoreUrl: String
        get() = StoreUtils.getStoreAppLink(context)

    override val mailUrl: String
        get() = context.getString(R.string.developer_mail_address)

    override val webSiteUrl: String
        get() = context.getString(R.string.developer_website_url)

    override val currentVersion: String
        get() = VersionUtils.getVersionName(context)

}
