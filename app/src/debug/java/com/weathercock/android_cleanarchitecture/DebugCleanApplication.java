package com.weathercock.android_cleanarchitecture;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

/**
 * Application Class for Debug
 * <p>
 * Created by weath on 2016/06/05.
 */
public class DebugCleanApplication extends CleanApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

}