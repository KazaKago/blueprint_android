package com.ignis.android_cleanarchitecture.domain.repository;

/**
 * About Repository
 *
 * @author Kensuke
 */
public interface AboutRepository {

    /**
     * PlayStore URLを取得する
     */
    String getPlayStoreUrl();

    /**
     * メールアドレスを取得する
     */
    String getMailUrl();

    /**
     * デベロッパーページURLを取得する
     */
    String getWebSiteUrl();

    /**
     * 現在のバージョン名を取得する
     *
     * @return
     */
    String getCurrentVersion();

}
