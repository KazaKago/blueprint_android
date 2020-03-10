package com.kazakago.cleanarchitecture.data.repository.hierarchy.about

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.R
import com.kazakago.cleanarchitecture.data.repository.global.dispatcher.FlowDispatcher
import com.kazakago.cleanarchitecture.data.resource.hierarchy.device.StoreLinkDao
import com.kazakago.cleanarchitecture.data.resource.hierarchy.device.VersionDao
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.*
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.about.AboutRepository
import kotlinx.coroutines.flow.Flow
import java.net.URL

internal class AboutRepositoryImpl(private val context: Context) : AboutRepository {

    private val versionDao = VersionDao(context)
    private val storeLinkDao = StoreLinkDao(context)

    override fun subscribeAppInfo(): Flow<State<AppInfo>> {
        return FlowDispatcher(
            fetch = { getAppInfo() }
        ).subscribe()
    }

    override fun subscribeDeveloperInfo(): Flow<State<DeveloperInfo>> {
        return FlowDispatcher(
            fetch = { getDeveloperInfo() }
        ).subscribe()
    }

    private fun getAppInfo(): AppInfo {
        return AppInfo(
            VersionName(versionDao.getVersionName()),
            VersionCode(versionDao.getVersionCode()),
            storeLinkDao.getStoreAppLink()
        )
    }

    private fun getDeveloperInfo(): DeveloperInfo {
        return DeveloperInfo(
            context.getString(R.string.developer_name),
            Email(context.getString(R.string.developer_mail_address)),
            URL(context.getString(R.string.developer_website_url))
        )
    }


}
