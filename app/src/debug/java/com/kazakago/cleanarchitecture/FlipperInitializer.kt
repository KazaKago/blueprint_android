package com.kazakago.cleanarchitecture

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader

fun Application.initializeFlipper() {
    SoLoader.init(this, false)
    if (FlipperUtils.shouldEnableFlipper(this)) {
        val client = AndroidFlipperClient.getInstance(this).apply {
            addPlugin(DatabasesFlipperPlugin(this@initializeFlipper))
            addPlugin(InspectorFlipperPlugin(this@initializeFlipper, DescriptorMapping.withDefaults()))
            addPlugin(SharedPreferencesFlipperPlugin(this@initializeFlipper))
            addPlugin(NetworkFlipperPlugin())
            addPlugin(CrashReporterPlugin.getInstance())
            addPlugin(NavigationFlipperPlugin.getInstance())
        }
        client.start()
    }
}
