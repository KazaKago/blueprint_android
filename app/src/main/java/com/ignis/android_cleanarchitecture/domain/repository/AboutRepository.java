package com.ignis.android_cleanarchitecture.domain.repository;

import rx.Observable;

/**
 * About Repository
 *
 * @author Kensuke
 */
public interface AboutRepository {

    /**
     * PlayStore URLを取得する
     */
    Observable<String> getPlayStoreUrl();

    /**
     * メールアドレスを取得する
     */
    Observable<String> getMailUrl();

    /**
     * デベロッパーページURLを取得する
     */
    Observable<String> getWebSiteUrl();

    /**
     * 現在のバージョン名を取得する
     *
     * @return
     */
    Observable<String> getCurrentVersion();

}
