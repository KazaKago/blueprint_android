package com.ignis.android_cleanarchitecture.data.repository;

import android.content.Context;

import com.ignis.android_cleanarchitecture.domain.model.ProfileModel;
import com.ignis.android_cleanarchitecture.domain.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Observable;

/**
 * Profile Repository Implement
 * <p>
 * Created by tamura_k on 2016/05/27.
 */
public class ProfileRepositoryImpl implements ProfileRepository {

    private Context context;

    public ProfileRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<List<ProfileModel>> selectAll() {
        return Observable.create((Observable.OnSubscribe<List<ProfileModel>>) subscriber -> {
            List<ProfileModel> profileList = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                ProfileModel profileModel = new ProfileModel("Jake", "Wharton");
                Calendar birthday = Calendar.getInstance();
                birthday.set(1980, 0, 1);
                profileModel.setBirthday(birthday.getTime());
                profileList.add(profileModel);
            }
            subscriber.onNext(profileList);
            subscriber.onCompleted();
        });
    }

}
