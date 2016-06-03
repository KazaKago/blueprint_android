package com.ignis.android_cleanarchitecture;

import android.app.Application;
import android.content.Context;

import com.ignis.android_cleanarchitecture.data.dao.RealmManager;
import com.ignis.android_cleanarchitecture.presentation.di.component.ApplicationComponent;
import com.ignis.android_cleanarchitecture.presentation.di.component.DaggerApplicationComponent;
import com.ignis.android_cleanarchitecture.presentation.di.module.ApplicationModule;
import com.ignis.android_cleanarchitecture.presentation.di.module.DataModule;
import com.ignis.android_cleanarchitecture.presentation.di.module.DomainModule;

/**
 * アプリケーションクラス
 *
 * @author kensuke
 */
public class CleanApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static CleanApplication getInstance(Context context) {
        return (CleanApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        RealmManager.initRealmConfiguration(this);
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .domainModule(new DomainModule())
                .dataModule(new DataModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
