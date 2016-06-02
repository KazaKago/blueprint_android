package com.ignis.android_cleanarchitecture.domain.repository;

import com.ignis.android_cleanarchitecture.domain.model.ProfileModel;

import java.util.List;

/**
 * Profile Repository
 *
 * @author Kensuke
 */
public interface ProfileRepository {

    /**
     * プロフィール一覧を取得する
     */
    List<ProfileModel> selectAll();

}
