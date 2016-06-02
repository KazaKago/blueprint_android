package com.ignis.android_cleanarchitecture.presentation.di.module;

import android.content.Context;

import com.ignis.android_cleanarchitecture.data.repository.AboutRepositoryImpl;
import com.ignis.android_cleanarchitecture.data.repository.ProfileRepositoryImpl;
import com.ignis.android_cleanarchitecture.domain.repository.AboutRepository;
import com.ignis.android_cleanarchitecture.domain.repository.ProfileRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public ProfileRepository provideProfileRepository(Context context) {
        return new ProfileRepositoryImpl(context);
    }

    @Provides
    @Singleton
    public AboutRepository provideAboutRepository(Context context) {
        return new AboutRepositoryImpl(context);
    }

}