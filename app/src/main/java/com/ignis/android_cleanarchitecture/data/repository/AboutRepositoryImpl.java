package com.ignis.android_cleanarchitecture.data.repository;

import android.content.Context;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.data.util.StoreUtils;
import com.ignis.android_cleanarchitecture.data.util.VersionUtils;
import com.ignis.android_cleanarchitecture.domain.repository.AboutRepository;

import rx.Observable;

/**
 * About Repository Implement
 * <p>
 * Created by tamura_k on 2016/05/27.
 */
public class AboutRepositoryImpl implements AboutRepository {

    private Context context;

    public AboutRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<String> getPlayStoreUrl() {
        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext(StoreUtils.getStoreDirectLink(context));
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<String> getMailUrl() {
        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext(context.getString(R.string.developer_mail_address));
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<String> getWebSiteUrl() {
        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext(context.getString(R.string.developer_website_url));
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<String> getCurrentVersion() {
        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext(VersionUtils.getVersionName(context));
            subscriber.onCompleted();
        });
    }

}
