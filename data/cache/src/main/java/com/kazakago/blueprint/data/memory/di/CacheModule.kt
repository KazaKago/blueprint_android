package com.kazakago.blueprint.data.memory.di

import com.kazakago.blueprint.data.memory.hierarchy.GithubCache
import org.koin.dsl.module

val cacheModule = module {
    single { GithubCache() }
}
