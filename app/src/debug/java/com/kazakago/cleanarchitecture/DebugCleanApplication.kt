package com.kazakago.cleanarchitecture

import com.facebook.stetho.Stetho

class DebugCleanApplication : CleanApplication() {

    override fun onCreate() {
        super.onCreate()
        initializeStetho()
    }

    private fun initializeStetho() {
        Stetho.initializeWithDefaults(this)
    }

}
