package com.ignis.android_cleanarchitecture.domain.usecase;

import com.ignis.android_cleanarchitecture.domain.model.ProfileModel;

import java.util.List;

/**
 * Profile UseCase
 *
 * @author Kensuke
 */
public interface ProfileUseCase {

    List<ProfileModel> getProfileList();

}
