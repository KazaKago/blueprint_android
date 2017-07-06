package com.kazakago.cleanarchitecture.domain.repository

/**
 * About Repository

 * @author Kensuke
 */
interface AppInfoRepository {

    val playStoreUrl: String

    val mailAddressUrl: String

    val officialSiteUrl: String

    val appVersion: String

}
