package com.kazakago.cleanarchitecture

import android.app.Application
import com.facebook.stetho.Stetho
import com.kazakago.cleanarchitecture.data.di.dataModule
import com.kazakago.cleanarchitecture.domain.di.domainModule
import com.kazakago.cleanarchitecture.presentation.di.presentationModule
import com.kazakago.cleanarchitecture.web.di.webModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CleanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
        initializeStetho()
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CleanApplication)
            modules(
                listOf(
                    presentationModule,
                    domainModule,
                    dataModule,
                    webModule
                )
            )
        }
    }

    private fun initializeStetho() {
        Stetho.initializeWithDefaults(this)
    }

}
