package com.kazakago.blueprint.domain.usecase.di

import com.kazakago.blueprint.domain.usecase.hierarchy.about.GetAboutUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.about.GetAboutUseCaseImpl
import com.kazakago.blueprint.domain.usecase.hierarchy.github.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindFollowGithubOrgsUseCase(followGithubOrgsUseCase: FollowGithubOrgsUseCaseImpl): FollowGithubOrgsUseCase

    @Singleton
    @Binds
    abstract fun bindFollowGithubReposUseCase(followGithubReposUseCase: FollowGithubReposUseCaseImpl): FollowGithubReposUseCase

    @Singleton
    @Binds
    abstract fun bindRefreshGithubOrgsUseCase(refreshGithubOrgsUseCase: RefreshGithubOrgsUseCaseImpl): RefreshGithubOrgsUseCase

    @Singleton
    @Binds
    abstract fun bindRefreshGithubReposUseCase(refreshGithubReposUseCase: RefreshGithubReposUseCaseImpl): RefreshGithubReposUseCase

    @Singleton
    @Binds
    abstract fun bindRequestAdditionalGithubOrgsUseCase(requestAdditionalGithubOrgsUseCase: RequestAdditionalGithubOrgsUseCaseImpl): RequestAdditionalGithubOrgsUseCase

    @Singleton
    @Binds
    abstract fun bindRequestAdditionalGithubReposUseCase(requestAdditionalGithubReposUseCase: RequestAdditionalGithubReposUseCaseImpl): RequestAdditionalGithubReposUseCase

    @Singleton
    @Binds
    abstract fun bindGetAboutUseCase(getAboutUseCase: GetAboutUseCaseImpl): GetAboutUseCase
}
