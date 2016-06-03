package com.ignis.android_cleanarchitecture.presentation.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class ActivityModule {

    private Realm realm;

    public ActivityModule(Realm realm) {
        this.realm = realm;
    }

    @Provides
    @Singleton
    public Realm provideRealm() {
        return realm;
    }

}