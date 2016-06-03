package com.ignis.android_cleanarchitecture.data.dao;

import android.content.Context;
import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * RealmManager
 * <p/>
 * Created by tamura_k on 2016/03/24.
 */
public class RealmManager {

    /**
     * Realmの初期化を行う
     *
     * @param context
     */
    public static void initRealmConfiguration(@NonNull Context context) {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
