package com.kazakago.blueprint

import android.app.Application
import com.kazakago.blueprint.data.repository.di.dataModule
import com.kazakago.blueprint.domain.usecase.di.domainModule
import com.kazakago.blueprint.presentation.viewmodel.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun Application.initializeKoin() {
    startKoin {
        androidLogger()
        androidContext(this@initializeKoin)
        modules(
            listOf(
                presentationModule,
                domainModule,
                dataModule
            )
        )
    }
}
