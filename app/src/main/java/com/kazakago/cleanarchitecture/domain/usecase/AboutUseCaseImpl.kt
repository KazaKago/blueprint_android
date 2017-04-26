package com.kazakago.cleanarchitecture.domain.usecase

import com.kazakago.cleanarchitecture.domain.repository.AboutRepository
import java.util.*

/**
 * About UseCase Implement
 *
 * @author Kensuke
 */
class AboutUseCaseImpl(private val aboutRepository: AboutRepository) : AboutUseCase {

    override val playStoreUrl: String
        get() = aboutRepository.playStoreUrl

    override val mailUrl: String
        get() = aboutRepository.mailUrl

    override val webSiteUrl: String
        get() = aboutRepository.webSiteUrl

    override val currentVersion: String
        get() = aboutRepository.currentVersion

    override val currentYear: Int
        get() {
            val calendar = Calendar.getInstance()
            return calendar.get(Calendar.YEAR)
        }

}
