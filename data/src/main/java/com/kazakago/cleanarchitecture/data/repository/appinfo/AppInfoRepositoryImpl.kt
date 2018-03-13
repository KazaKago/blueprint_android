package com.kazakago.cleanarchitecture.data.repository.appinfo

import android.content.Context
import com.kazakago.cleanarchitecture.data.R
import com.kazakago.cleanarchitecture.data.util.StoreUtils
import com.kazakago.cleanarchitecture.data.util.VersionUtils
import com.kazakago.cleanarchitecture.domain.model.appinfo.AppInfo
import com.kazakago.cleanarchitecture.domain.repository.appinfo.AppInfoRepository
import io.reactivex.Single
import java.net.URL

class AppInfoRepositoryImpl(private val context: Context) : AppInfoRepository {

    override fun getAppInfo(): Single<AppInfo> {
        return Single.just(
                AppInfo(context.getString(R.string.developer_name),
                        VersionUtils.getVersionName(context),
                        VersionUtils.getVersionCode(context),
                        StoreUtils.getStoreAppLink(context),
                        context.getString(R.string.developer_mail_address),
                        URL(context.getString(R.string.developer_website_url))))
    }

}
