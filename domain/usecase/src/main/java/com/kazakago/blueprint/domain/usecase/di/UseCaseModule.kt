package com.kazakago.blueprint.domain.usecase.di

import com.kazakago.blueprint.domain.usecase.hierarchy.about.GetAboutUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.about.GetAboutUseCaseImpl
import com.kazakago.blueprint.domain.usecase.hierarchy.github.*
import org.koin.dsl.module

val useCaseModule = module {
    single<FollowGithubOrgsUseCase> { FollowGithubOrgsUseCaseImpl(get()) }
    single<FollowGithubReposUseCase> { FollowGithubReposUseCaseImpl(get()) }
    single<RefreshGithubOrgsUseCase> { RefreshGithubOrgsUseCaseImpl(get()) }
    single<RefreshGithubReposUseCase> { RefreshGithubReposUseCaseImpl(get()) }
    single<RequestAdditionalGithubOrgsUseCase> { RequestAdditionalGithubOrgsUseCaseImpl(get()) }
    single<RequestAdditionalGithubReposUseCase> { RequestAdditionalGithubReposUseCaseImpl(get()) }
    single<GetAboutUseCase> { GetAboutUseCaseImpl(get()) }
}
