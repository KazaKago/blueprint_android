package com.kazakago.blueprint.data.api.di

import com.kazakago.blueprint.data.api.global.ApiRequester
import com.kazakago.blueprint.data.api.hierarchy.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideGithubService(apiRequester: ApiRequester): GithubService {
        return apiRequester.create(GithubService::class)
    }
}
