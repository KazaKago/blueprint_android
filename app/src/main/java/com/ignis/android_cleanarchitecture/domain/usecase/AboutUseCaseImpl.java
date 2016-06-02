package com.ignis.android_cleanarchitecture.domain.usecase;

import com.ignis.android_cleanarchitecture.domain.repository.AboutRepository;

import java.util.Calendar;

import rx.Observable;

/**
 * About UseCase Implement
 *
 * @author Kensuke
 */
public class AboutUseCaseImpl implements AboutUseCase {

    private AboutRepository aboutRepository;

    public AboutUseCaseImpl(AboutRepository aboutRepository) {
        this.aboutRepository = aboutRepository;
    }

    @Override
    public Observable<String> getPlayStoreUrl() {
        return aboutRepository.getPlayStoreUrl();
    }

    @Override
    public Observable<String> getMailUrl() {
        return aboutRepository.getMailUrl();
    }

    @Override
    public Observable<String> getWebSiteUrl() {
        return aboutRepository.getWebSiteUrl();
    }

    @Override
    public Observable<String> getCurrentVersion() {
        return aboutRepository.getCurrentVersion();
    }

    @Override
    public Observable<Integer> getCurrentYear() {
        return Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            Calendar calendar = Calendar.getInstance();
            subscriber.onNext(calendar.get(Calendar.YEAR));
            subscriber.onCompleted();
        });
    }

}
