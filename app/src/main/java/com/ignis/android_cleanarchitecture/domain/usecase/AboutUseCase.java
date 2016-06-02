package com.ignis.android_cleanarchitecture.domain.usecase;

import rx.Observable;

/**
 * About UseCase
 *
 * @author Kensuke
 */
public interface AboutUseCase {

    Observable<String> getPlayStoreUrl();

    Observable<String> getMailUrl();

    Observable<String> getWebSiteUrl();

    Observable<String> getCurrentVersion();

    Observable<Integer> getCurrentYear();

}
