package com.kazakago.cleanarchitecture

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
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
        initializeFlipper()
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

    private fun initializeFlipper() {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this).apply {
                addPlugin(DatabasesFlipperPlugin(this@CleanApplication))
                addPlugin(InspectorFlipperPlugin(this@CleanApplication, DescriptorMapping.withDefaults()))
                addPlugin(SharedPreferencesFlipperPlugin(this@CleanApplication))
                addPlugin(NetworkFlipperPlugin())
                addPlugin(CrashReporterPlugin.getInstance())
            }
            client.start()
        }
    }

}
