package com.kazakago.blueprint

import android.app.Application
import com.kazakago.blueprint.data.api.di.apiModule
import com.kazakago.blueprint.data.mapper.di.mapperModule
import com.kazakago.blueprint.data.memory.di.cacheModule
import com.kazakago.blueprint.data.repository.di.repositoryModule
import com.kazakago.blueprint.data.resource.di.resourceModule
import com.kazakago.blueprint.domain.usecase.di.useCaseModule
import com.kazakago.blueprint.presentation.viewmodel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.initializeKoin() {
    startKoin {
        androidLogger()
        androidContext(this@initializeKoin)
        modules(
            listOf(
                viewModelModule,
                useCaseModule,
                repositoryModule,
                apiModule,
                cacheModule,
                mapperModule,
                resourceModule,
            )
        )
    }
}
