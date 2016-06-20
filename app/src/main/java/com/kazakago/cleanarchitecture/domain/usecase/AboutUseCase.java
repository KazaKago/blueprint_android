package com.kazakago.cleanarchitecture.domain.usecase;

/**
 * About UseCase
 *
 * @author Kensuke
 */
public interface AboutUseCase {

    String getPlayStoreUrl();

    String getMailUrl();

    String getWebSiteUrl();

    String getCurrentVersion();

    int getCurrentYear();

}
