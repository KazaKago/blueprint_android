package com.weathercock.android_cleanarchitecture.domain.usecase;

import com.weathercock.android_cleanarchitecture.domain.repository.AboutRepository;

import java.util.Calendar;

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
    public String getPlayStoreUrl() {
        return aboutRepository.getPlayStoreUrl();
    }

    @Override
    public String getMailUrl() {
        return aboutRepository.getMailUrl();
    }

    @Override
    public String getWebSiteUrl() {
        return aboutRepository.getWebSiteUrl();
    }

    @Override
    public String getCurrentVersion() {
        return aboutRepository.getCurrentVersion();
    }

    @Override
    public int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

}
