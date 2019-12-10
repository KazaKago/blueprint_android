package com.kazakago.cleanarchitecture.usecase.stub.data.repository.about

import com.kazakago.cleanarchitecture.model.about.*
import com.kazakago.cleanarchitecture.repository.about.AboutRepository
import java.net.URI
import java.net.URL

class AboutRepositoryStub : AboutRepository {

    override fun getAppInfo(): AppInfo {
        return AppInfo(
            versionName = VersionName("1.1.0"),
            versionCode = VersionCode(10),
            playStoreUri = URI("http://hogehoge.co.jp")
        )
    }

    override fun getDeveloperInfo(): DeveloperInfo {
        return DeveloperInfo(
            name = "FullName",
            mailAddress = Email("kazakago@gmail.com"),
            siteUrl = URL("http://kazakago.hatenablog.jp/")
        )
    }

}