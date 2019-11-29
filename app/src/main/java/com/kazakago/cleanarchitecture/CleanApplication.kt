package com.kazakago.cleanarchitecture

import android.app.Application

class CleanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
        initializeFlipper()
    }

}
