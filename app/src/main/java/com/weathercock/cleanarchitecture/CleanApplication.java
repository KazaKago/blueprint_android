package com.weathercock.cleanarchitecture;

import android.app.Application;
import android.content.Context;

import com.weathercock.cleanarchitecture.di.component.ApplicationComponent;
import com.weathercock.cleanarchitecture.di.component.DaggerApplicationComponent;
import com.weathercock.cleanarchitecture.di.module.ApplicationModule;
import com.weathercock.cleanarchitecture.di.module.DataModule;
import com.weathercock.cleanarchitecture.di.module.DomainModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
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
