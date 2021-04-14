package com.kazakago.blueprint.data.resource.di

import com.kazakago.blueprint.data.resource.hierarchy.about.DeveloperInfoDao
import com.kazakago.blueprint.data.resource.hierarchy.device.StoreDao
import com.kazakago.blueprint.data.resource.hierarchy.device.VersionDao
import org.koin.dsl.module

val resourceModule = module {
    single { DeveloperInfoDao(get()) }
    single { StoreDao(get()) }
    single { VersionDao(get()) }
}
