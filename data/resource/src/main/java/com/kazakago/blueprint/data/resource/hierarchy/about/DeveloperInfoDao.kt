package com.kazakago.blueprint.data.resource.hierarchy.about

import android.content.Context
import com.kazakago.blueprint.data.resource.R
import java.net.URI
import java.net.URL

class DeveloperInfoDao(private val context: Context) {

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
