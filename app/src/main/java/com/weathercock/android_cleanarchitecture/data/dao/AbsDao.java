package com.weathercock.android_cleanarchitecture.data.dao;

import android.support.annotation.NonNull;

import io.realm.Realm;

/**
 * Abstract Data Access Object
 */
public abstract class AbsDao {

    @NonNull
    private Realm realm;

    public AbsDao(@NonNull Realm realm) {
        this.realm = realm;
    }

    @NonNull
    public Realm getRealm() {
        return realm;
    }

}
