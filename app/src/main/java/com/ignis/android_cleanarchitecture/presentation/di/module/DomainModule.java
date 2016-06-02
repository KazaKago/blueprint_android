package com.ignis.android_cleanarchitecture.presentation.di.module;

import com.ignis.android_cleanarchitecture.domain.repository.AboutRepository;
import com.ignis.android_cleanarchitecture.domain.repository.ProfileRepository;
import com.ignis.android_cleanarchitecture.domain.usecase.AboutUseCase;
import com.ignis.android_cleanarchitecture.domain.usecase.AboutUseCaseImpl;
import com.ignis.android_cleanarchitecture.domain.usecase.ProfileUseCase;
import com.ignis.android_cleanarchitecture.domain.usecase.ProfileUseCaseImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {

    @Provides
    @Singleton
    public ProfileUseCase provideProfileUseCase(ProfileRepository profileRepository) {
        return new ProfileUseCaseImpl(profileRepository);
    }

    @Provides
    @Singleton
    public AboutUseCase provideAboutUseCase(AboutRepository aboutRepository) {
        return new AboutUseCaseImpl(aboutRepository);
    }

}