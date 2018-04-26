package com.kazakago.cleanarchitecture

import android.app.Application
import com.kazakago.cleanarchitecture.data.di.dataModule
import com.kazakago.cleanarchitecture.domain.di.domainModule
import com.kazakago.cleanarchitecture.presentation.di.presentationModule
import com.kazakago.cleanarchitecture.web.di.webModule
import org.koin.android.ext.android.startKoin

open class CleanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin(this, listOf(
                presentationModule,
                domainModule,
                dataModule,
                webModule
        ))
    }

}
