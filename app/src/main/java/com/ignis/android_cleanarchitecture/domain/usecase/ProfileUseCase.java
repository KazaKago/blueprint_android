package com.ignis.android_cleanarchitecture.domain.usecase;

import com.ignis.android_cleanarchitecture.domain.model.ProfileModel;

import java.util.List;

import rx.Observable;

/**
 * Profile UseCase
 *
 * @author Kensuke
 */
public interface ProfileUseCase {

    Observable<List<ProfileModel>> getProfileList();

}
