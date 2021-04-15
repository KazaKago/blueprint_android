package com.kazakago.blueprint.data.repository.di

import com.kazakago.blueprint.data.repository.hierarchy.AboutRepositoryImpl
import com.kazakago.blueprint.data.repository.hierarchy.GithubRepositoryImpl
import com.kazakago.blueprint.domain.repository.hierarchy.AboutRepository
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideGithubRepository(githubRepository: GithubRepositoryImpl): GithubRepository

    @Singleton
    @Binds
    abstract fun provideAboutRepository(aboutRepositoryImpl: AboutRepositoryImpl): AboutRepository
}
