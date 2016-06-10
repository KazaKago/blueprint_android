package com.weathercock.android_cleanarchitecture.domain.repository;

/**
 * About Repository
 *
 * @author Kensuke
 */
public interface AboutRepository {

    String getPlayStoreUrl();

    String getMailUrl();

    String getWebSiteUrl();

    String getCurrentVersion();

}
