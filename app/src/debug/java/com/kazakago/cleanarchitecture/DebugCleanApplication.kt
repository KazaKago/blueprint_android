package com.kazakago.cleanarchitecture

import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary

class DebugCleanApplication : CleanApplication() {

    override fun onCreate() {
        super.onCreate()
        initializeStetho()
        initializeLeakCanary()
    }

    private fun initializeStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initializeLeakCanary() {
        LeakCanary.install(this)
    }

}
