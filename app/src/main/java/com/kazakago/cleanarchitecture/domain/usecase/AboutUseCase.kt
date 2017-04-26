package com.kazakago.cleanarchitecture.domain.usecase

/**
 * About UseCase
 *
 * @author Kensuke
 */
interface AboutUseCase {

    val playStoreUrl: String

    val mailUrl: String

    val webSiteUrl: String

    val currentVersion: String

    val currentYear: Int

}
