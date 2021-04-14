package com.kazakago.blueprint.data.api.di

import com.kazakago.blueprint.data.api.global.ApiRequester
import com.kazakago.blueprint.data.api.hierarchy.GithubService
import org.koin.dsl.module

val apiModule = module {
    single { ApiRequester() }
    single { get<ApiRequester>().create(GithubService::class) }
}
