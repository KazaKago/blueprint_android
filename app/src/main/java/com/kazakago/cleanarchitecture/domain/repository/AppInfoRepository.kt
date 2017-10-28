package com.kazakago.cleanarchitecture.domain.repository

interface AppInfoRepository {

    val playStoreUrl: String

    val mailAddressUrl: String

    val officialSiteUrl: String

    val appVersion: String

}
