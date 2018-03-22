package com.kazakago.cleanarchitecture.domain.usecase.about

import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.repository.about.AboutRepository
import io.reactivex.Single

class GetDeveloperInfoUseCaseImpl(private val aboutRepository: AboutRepository) : GetDeveloperInfoUseCase {

    override fun execute(input: Unit): Single<DeveloperInfo> {
        return aboutRepository.getDeveloperInfo()
    }

}