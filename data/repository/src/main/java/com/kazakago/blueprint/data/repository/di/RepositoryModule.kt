package com.kazakago.blueprint.data.repository.di

import com.kazakago.blueprint.data.repository.hierarchy.AboutRepositoryImpl
import com.kazakago.blueprint.data.repository.hierarchy.GithubRepositoryImpl
import com.kazakago.blueprint.domain.repository.hierarchy.AboutRepository
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideGithubRepository(githubRepository: GithubRepositoryImpl): GithubRepository

    @Binds
    abstract fun provideAboutRepository(aboutRepositoryImpl: AboutRepositoryImpl): AboutRepository
}
