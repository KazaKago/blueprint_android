package com.kazakago.cleanarchitecture

import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider

/**
 * Application Class for Debug
 *
 * Created by weath on 2016/06/05.
 */
class DebugCleanApplication : CleanApplication() {

    override fun onCreate() {
        super.onCreate()
        initializeStetho()
    }

    private fun initializeStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build())
    }

}