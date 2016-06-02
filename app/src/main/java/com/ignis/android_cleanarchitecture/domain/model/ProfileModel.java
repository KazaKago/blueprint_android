package com.ignis.android_cleanarchitecture.domain.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Profile Model
 * <p/>
 * Created by tamura_k on 2016/05/31.
 */
public class ProfileModel {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Nullable
    private Date birthday;

    public ProfileModel(@NonNull String firstName, @NonNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @Nullable
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(@Nullable Date birthday) {
        this.birthday = birthday;
    }

}
