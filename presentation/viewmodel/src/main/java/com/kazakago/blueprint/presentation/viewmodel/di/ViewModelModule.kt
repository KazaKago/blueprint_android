package com.kazakago.blueprint.presentation.viewmodel.di

import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.about.AboutViewModel
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.github.GithubOrgsViewModel
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.github.GithubReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GithubOrgsViewModel(get(), get(), get()) }
    viewModel { (githubOrgName: GithubOrgName) -> GithubReposViewModel(get(), get(), get(), githubOrgName) }
    viewModel { AboutViewModel(get(), get()) }
}
