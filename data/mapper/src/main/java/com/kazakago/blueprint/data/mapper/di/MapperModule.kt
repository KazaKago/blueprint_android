package com.kazakago.blueprint.data.mapper.di

import com.kazakago.blueprint.data.mapper.hierarchy.entity.about.AppInfoEntityMapper
import com.kazakago.blueprint.data.mapper.hierarchy.entity.about.DeveloperInfoEntityMapper
import com.kazakago.blueprint.data.mapper.hierarchy.entity.github.GithubOrgEntityMapper
import com.kazakago.blueprint.data.mapper.hierarchy.entity.github.GithubRepoEntityMapper
import com.kazakago.blueprint.data.mapper.hierarchy.response.github.GithubOrgResponseMapper
import com.kazakago.blueprint.data.mapper.hierarchy.response.github.GithubRepoResponseMapper
import org.koin.dsl.module

val mapperModule = module {
    single { GithubOrgResponseMapper() }
    single { GithubRepoResponseMapper() }
    single { AppInfoEntityMapper() }
    single { DeveloperInfoEntityMapper() }
    single { GithubOrgEntityMapper() }
    single { GithubRepoEntityMapper() }
}
