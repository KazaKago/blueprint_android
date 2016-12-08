package com.kazakago.cleanarchitecture;

import android.app.Application;

import com.kazakago.cleanarchitecture.di.component.ApplicationComponent;
import com.kazakago.cleanarchitecture.di.component.DaggerApplicationComponent;
import com.kazakago.cleanarchitecture.di.module.ApplicationModule;
import com.kazakago.cleanarchitecture.di.module.DataModule;
import com.kazakago.cleanarchitecture.di.module.DomainModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * アプリケーションクラス
 *
 * @author kensuke
 */
public class CleanApplication extends Application {

    public static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .domainModule(new DomainModule())
                .dataModule(new DataModule())
                .build();
    }

}
