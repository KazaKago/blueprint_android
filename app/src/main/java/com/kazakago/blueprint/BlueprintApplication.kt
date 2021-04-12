package com.kazakago.blueprint

import android.app.Application

class BlueprintApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
        initializeFlipper()
    }
}
