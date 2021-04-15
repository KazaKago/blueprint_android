package com.kazakago.blueprint.domain.usecase.di

import com.kazakago.blueprint.domain.usecase.hierarchy.about.GetAboutUseCase
import com.kazakago.blueprint.domain.usecase.hierarchy.about.GetAboutUseCaseImpl
import com.kazakago.blueprint.domain.usecase.hierarchy.github.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindFollowGithubOrgsUseCase(followGithubOrgsUseCase: FollowGithubOrgsUseCaseImpl): FollowGithubOrgsUseCase

    @Binds
    abstract fun bindFollowGithubReposUseCase(followGithubReposUseCase: FollowGithubReposUseCaseImpl): FollowGithubReposUseCase

    @Binds
    abstract fun bindRefreshGithubOrgsUseCase(refreshGithubOrgsUseCase: RefreshGithubOrgsUseCaseImpl): RefreshGithubOrgsUseCase

    @Binds
    abstract fun bindRefreshGithubReposUseCase(refreshGithubReposUseCase: RefreshGithubReposUseCaseImpl): RefreshGithubReposUseCase

    @Binds
    abstract fun bindRequestAdditionalGithubOrgsUseCase(requestAdditionalGithubOrgsUseCase: RequestAdditionalGithubOrgsUseCaseImpl): RequestAdditionalGithubOrgsUseCase

    @Binds
    abstract fun bindRequestAdditionalGithubReposUseCase(requestAdditionalGithubReposUseCase: RequestAdditionalGithubReposUseCaseImpl): RequestAdditionalGithubReposUseCase

    @Binds
    abstract fun bindGetAboutUseCase(getAboutUseCase: GetAboutUseCaseImpl): GetAboutUseCase
}
