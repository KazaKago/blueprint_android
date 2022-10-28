package com.kazakago.blueprint.data.di

import com.kazakago.blueprint.data.api.ApiRequester
import com.kazakago.blueprint.data.api.github.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Singleton
    @Provides
    fun provideGithubService(apiRequester: ApiRequester): GithubApi {
        return apiRequester.create(GithubApi::class)
    }
}
