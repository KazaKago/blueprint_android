package com.kazakago.blueprint.data.resource

import android.content.Context
import com.kazakago.blueprint.data.repository.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.URI
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeveloperInfoDao @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun getName(): String {
        return context.getString(R.string.developer_name)
    }

    fun getEmailAddress(): URI {
        return URI(context.getString(R.string.developer_mail_address))
    }

    fun getWebSiteUrl(): URL {
        return URL(context.getString(R.string.developer_website_url))
    }
}
