package com.kazakago.blueprint.data.di

import com.kazakago.blueprint.data.api.ApiRequester
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Singleton
    @Provides
    fun provideHttpClient(apiRequester: ApiRequester): HttpClient {
        return apiRequester.httpClient
    }
}
