package com.kazakago.cleanarchitecture.domain.repository.appinfo

interface AppInfoRepository {

    val playStoreUrl: String

    val mailAddressUrl: String

    val officialSiteUrl: String

    val appVersion: String

}
