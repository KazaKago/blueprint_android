package com.kazakago.blueprint.data.repository.di

import com.kazakago.blueprint.data.repository.hierarchy.AboutRepositoryImpl
import com.kazakago.blueprint.data.repository.hierarchy.GithubRepositoryImpl
import com.kazakago.blueprint.domain.repository.AboutRepository
import com.kazakago.blueprint.domain.repository.GithubRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<GithubRepository> { GithubRepositoryImpl(get(), get(), get(), get(), get(), get()) }
    single<AboutRepository> { AboutRepositoryImpl(get(), get(), get(), get(), get()) }
}
